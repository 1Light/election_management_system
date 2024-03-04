package database;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Linker {
    private static List<Voter> voters = new ArrayList<>();
    private static Voter loggedInVoter;
    private static Candidate loggedInCandidate;
    private static Admin loggedInAdmin;

    // JDBC connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cbits";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1QAZ2WSX";

    static {
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void storeVoterInfo(String fullName, String phoneNumber, String age, String email, String password, String randomId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO voters (fullName, phoneNumber, age, email, password, id, accountStatus, voteStatus) VALUES (?, ?, ?, ?, ?, ?, 'Active', false)")) {

            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setString(3, age);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, randomId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void storeVote(String userId, String candidateId, JTextField yu, String position) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // Update the votes in the database for the specific position
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE candidates SET votes = votes + 1 WHERE id = ? AND position = ?")) {
                preparedStatement.setString(1, candidateId);
                preparedStatement.setString(2, position);
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    // Fetch the updated votes for the candidate and position
                    int currentVotes = getCurrentVotesForCandidate(candidateId, position);

                    // Update the JTextField with the current votes
                    yu.setText(String.valueOf(currentVotes));

                    // Store the vote in the voters table with the position
                    updateVoteStatus(userId, true, position);
                } else {
                    // Handle the case when no rows were affected (e.g., candidate not found for the position)
                    System.err.println("Error: Candidate not found for ID " + candidateId + " and position " + position);
                }
            }
        } catch (SQLException e) {
            // Handle database connection or query execution errors
            e.printStackTrace();
        }
    }

    public static int getCurrentVotesForCandidate(String candidateId, String position) {
        // Retrieve and return the current votes from the database for the specific position
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT votes FROM candidates WHERE id = ? AND position = ?")) {
            preparedStatement.setString(1, candidateId);
            preparedStatement.setString(2, position);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("votes");
            } else {
                return 0; // Default to 0 if no votes are found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static void storeCandidateInfo(String fullName, String phoneNumber, String age, String email, String password, String position, String imagePath, String randomId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO candidates (id, fullName, phoneNumber, age, email, password, position, imagePath, votes, accountStatus) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0, 'Active')")) {

            preparedStatement.setString(1, randomId);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, age);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, position);
            preparedStatement.setString(8, imagePath);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidLogin(String userType, String email, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String tableName = (userType.equals("Voter")) ? "voters" : ((userType.equals("Candidate")) ? "candidates" : "admin");
            String query = "SELECT * FROM " + tableName + " WHERE email = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    if (userType.equals("Voter")) {
                        loggedInVoter = new Voter(resultSet.getString("fullName"),
                                resultSet.getString("phoneNumber"),
                                resultSet.getString("age"),
                                resultSet.getString("email"),
                                resultSet.getString("id"),
                                resultSet.getString("password"));
                    }
                    else if (userType.equals("Admin")) {
                        loggedInAdmin = new Admin(resultSet.getString("email"),
                                resultSet.getString("password"));}
                    else {
                        loggedInCandidate = new Candidate(resultSet.getString("fullName"),
                                resultSet.getString("phoneNumber"),
                                resultSet.getString("age"),
                                resultSet.getString("email"),
                                resultSet.getString("password"));
                    }
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Voter getLoggedInUser() {
        return loggedInVoter;
    }

    public static Candidate getLoggedInCandidate() {
        return loggedInCandidate;
    }
    public static Admin getLoggedInAdmin() {
        return loggedInAdmin;
    }

    public static List<Candidate> getCandidates() {
        List<Candidate> candidates = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            String query = "SELECT id, fullName, position, imagePath FROM candidates";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String fullName = resultSet.getString("fullName");
                String position = resultSet.getString("position");
                String imagePath = resultSet.getString("imagePath");

                // Candidate fg = new Candidate(name, position);
                Candidate candidate = new Candidate(id, fullName, position, imagePath);
                candidates.add(candidate);
                // candidates.add(fg);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidates;
    }
    public static boolean hasUserVotedForPosition(String userId, String position) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT voteStatus FROM voters WHERE id = ? AND position = ?")) {
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, position);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // If the user's voteStatus is true, return true (user has voted for the position)
                return resultSet.getBoolean("voteStatus");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void updateVoteStatus(String voterId, boolean hasVoted, String position) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // Update the voteStatus in the database with the position
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE voters SET voteStatus = ?, position = ? WHERE id = ?")) {
                preparedStatement.setBoolean(1, hasVoted);
                preparedStatement.setString(2, position);
                preparedStatement.setString(3, voterId);
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows <= 0) {
                    // Handle the case when no rows were affected (e.g., voter not found)
                    System.err.println("Error: Voter not found for ID " + voterId);
                }
            }
        } catch (SQLException e) {
            // Handle database connection or query execution errors
            e.printStackTrace();
        }
    }

    /*public static void updateVoteStatusForPosition(String voterId, String position, boolean hasVoted) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // Update the voteStatus in the database for the specific position
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE voters SET voteStatus = ? WHERE id = ? AND position = ?")) {
                preparedStatement.setBoolean(1, hasVoted);
                preparedStatement.setString(2, voterId);
                preparedStatement.setString(3, position);
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows <= 0) {
                    // Handle the case when no rows were affected (e.g., voter not found for the position)
                    System.err.println("Error: Voter not found for ID " + voterId + " and position " + position);
                }
            }
        } catch (SQLException e) {
            // Handle database connection or query execution errors
            e.printStackTrace();
        }
    }*/

    public static List<String> getCandidateNamesForPosition(String position) {
        List<String> candidateNames = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT fullName FROM candidates WHERE position = ?")) {
            preparedStatement.setString(1, position);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                candidateNames.add(resultSet.getString("fullName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidateNames;
    }
    public static List<Candidate> getAllCandidateForPosition(String position) {
        List<Candidate> candidateNames = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT fullName, id, imagePath FROM candidates WHERE position = ?")) {
            preparedStatement.setString(1, position);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String fullName = resultSet.getString("fullName");
                String randomId = resultSet.getString("id");
                String imagePath = resultSet.getString("imagePath");
                Candidate candidate = new Candidate(fullName, randomId, imagePath, position);
                candidateNames.add(candidate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidateNames;
    }

    public static List<Integer> getVotesForCandidates(String position) {
        List<Integer> votes = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT votes FROM candidates WHERE position = ?")) {
            preparedStatement.setString(1, position);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                votes.add(resultSet.getInt("votes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return votes;
    }
    public static List<Voter> getVoters() {
        List<Voter> voters = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM voters";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String fullName = resultSet.getString("fullName");
                String phoneNumber = resultSet.getString("phoneNumber");
                String age = resultSet.getString("age");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                boolean voteStatus = resultSet.getBoolean("voteStatus");
                String id = resultSet.getString("id");
                String accountStatus = resultSet.getString("accountStatus");
                String remove = resultSet.getString("remove");

                Voter voter = new Voter(fullName, phoneNumber, age, email, password, voteStatus, remove, id, accountStatus);
                voters.add(voter);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return voters;
    }
    public static List<Candidate> getZCandidates() {
        List<Candidate> candidates = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM candidates";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String fullName = resultSet.getString("fullName");
                String phoneNumber = resultSet.getString("phoneNumber");
                String age = resultSet.getString("age");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String id = resultSet.getString("id");
                String accountStatus = resultSet.getString("accountStatus");
                String remove = resultSet.getString("remove");

                Candidate candidate = new Candidate(fullName, phoneNumber, age, email, password, remove, id, accountStatus);
                candidates.add(candidate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidates;
    }
    public static void removeVoterByID(String voterID) throws SQLException {
        String deleteQuery = "DELETE FROM voters WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setString(1, voterID);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error removing voter by ID", e);
        }
    }
    public static void removeCandidateByID(String candidateID) throws SQLException {
        String deleteQuery = "DELETE FROM candidates WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setString(1, candidateID);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error removing candidate by ID", e);
        }
    }
    public static void updateAccountStatus(int voterID, String status) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("UPDATE voters SET accountStatus = ? WHERE voterID = ?")) {

            statement.setString(1, status);
            statement.setInt(2, voterID);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // rethrow the exception
        }
    }
}




