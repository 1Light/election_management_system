package gui;

import database.Linker;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SignUpV {
    JFrame jf;
    JSeparator line;
    JTextField jt2, jt3, jt4, jt5, jt6, jt7;
    JLabel jt, jl, jl2, jl3, jl4, jl5, jl6, ja;
    JButton jb, jb3;

    public SignUpV() {
        jf = new JFrame("SignUp Page");

        Color var = new Color(255, 128, 0);
        Color var2 = new Color(0, 128, 255);

        line = new JSeparator(SwingConstants.HORIZONTAL);
        line.setBounds(17, 50, 550, 5);
        line.setBackground(var2);

        Font font2 = new Font("TIMES NEW ROMAN", Font.BOLD, 18);
        Font font3 = new Font("TIMES NEW ROMAN", Font.BOLD, 16);

        jb3 = new JButton("Back");
        jb3.setFont(font3);
        jb3.setBackground(var2);
        jb3.setForeground(Color.WHITE);
        jb3.setBounds(360, 430, 100, 25);
        jb3.addActionListener(e -> {
            new Main();
            jf.dispose();
        });

        // Creating text field
        jt = new JLabel("Welcome to ByteBallot!!!");
        jt.setBounds(0, 0, 600, 50);
        jt.setBackground(var);
        jt.setForeground(Color.BLACK);

        // Setting the font size of the text field
        Font font = new Font("TIMES NEW ROMAN", Font.BOLD, 30);
        jt.setFont(font);
        jt.setHorizontalAlignment(JLabel.CENTER);

        // Creating the opening text area
        ja = new JLabel("Please enter your credentials below");
        ja.setFont(font2);
        ja.setBounds(50, 60, 300, 25);

        // Creating the name and password text fields
        jt2 = new JTextField();
        jt2.setBounds(350, 100, 150, 25);
        jt2.setForeground(Color.BLACK);
        jt3 = new JTextField();
        jt3.setBounds(350, 150, 150, 25);
        jt3.setForeground(Color.BLACK);
        jt4 = new JTextField();
        jt4.setBounds(350, 200, 150, 25);
        jt4.setForeground(Color.BLACK);
        jt5 = new JTextField();
        jt5.setBounds(350, 250, 150, 25);
        jt5.setForeground(Color.BLACK);
        jt6 = new JTextField();
        jt6.setBounds(350, 300, 150, 25);
        jt6.setForeground(Color.BLACK);
        jt7 = new JTextField();
        jt7.setBounds(350, 350, 150, 25);
        jt7.setForeground(Color.BLACK);

        // Creating the name and password labels
        jl = new JLabel("Full Name");
        jl.setBounds(100, 100, 100, 25);
        jl.setFont(font3);
        jl.setForeground(Color.WHITE);
        jl2 = new JLabel("Phone Number");
        jl2.setBounds(100, 150, 150, 25);
        jl2.setForeground(Color.WHITE);
        jl2.setFont(font3);
        jl3 = new JLabel("Age");
        jl3.setBounds(100, 200, 100, 25);
        jl3.setForeground(Color.WHITE);
        jl3.setFont(font3);
        jl4 = new JLabel("Email");
        jl4.setBounds(100, 250, 100, 25);
        jl4.setForeground(Color.WHITE);
        jl4.setFont(font3);
        jl5 = new JLabel("Password");
        jl5.setBounds(100, 300, 100, 25);
        jl5.setForeground(Color.WHITE);
        jl5.setFont(font3);
        jl6 = new JLabel("Confirm Password");
        jl6.setBounds(100, 350, 150, 25);
        jl6.setForeground(Color.WHITE);
        jl6.setFont(font3);

        // Creating the signup and login buttons
        jb = new JButton("SignUp");
        // Assuming jb is your JButton
        jb.setBounds(100, 430, 100, 25);
        jb.setFont(font3);
        jb.setBackground(var2);
        jb.setForeground(Color.WHITE);
        jb.addActionListener(e -> {
            if (validateInputs()) {
                String randomId = generateRandomId();
                String fullName = jt2.getText();
                String phoneNumber = jt3.getText();
                String age = jt4.getText();
                String email = jt5.getText();
                String password = jt6.getText();
                Linker.storeVoterInfo(fullName, phoneNumber, age, email, password, randomId);
                JOptionPane.showMessageDialog(jf, "SignUp Successful!");
                jf.dispose();
                new Main();
            } else {
                JOptionPane.showMessageDialog(jf, "Please fill in all fields correctly.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Adding components to the frame
        jf.add(jt);
        jf.add(jt2);
        jf.add(jt3);
        jf.add(jl);
        jf.add(jl2);
        jf.add(jb);
        jf.add(jt4);
        jf.add(jt5);
        jf.add(jt6);
        jf.add(jt7);
        jf.add(jl3);
        jf.add(jl4);
        jf.add(jl5);
        jf.add(jl6);
        jf.add(ja);
        jf.add(jb3);
        jf.add(line);
        jf.add(jb3);

        // Finalizing input
        jf.getContentPane().setBackground(var);
        jf.setSize(600, 550);
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);
        jf.setVisible(true);
    }

    private boolean validateInputs() {
        String fullName = jt2.getText();
        String phoneNumber = jt3.getText();
        String age = jt4.getText();
        String email = jt5.getText();
        String password = jt6.getText();
        String confirmPassword = jt7.getText();

        if (fullName.isEmpty() || !fullName.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(jf, "Please enter a valid name.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (phoneNumber.isEmpty() || !phoneNumber.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(jf, "Please enter a valid 10-digit phone number.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (age.isEmpty() || !age.matches("\\d+") || Integer.parseInt(age) < 18) {
            JOptionPane.showMessageDialog(jf, "Please enter a valid age of 18 or above.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (email.isEmpty() || !email.matches("\\S+@\\S+\\.com")) {
            JOptionPane.showMessageDialog(jf, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (password.isEmpty() || !password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(jf, "Please enter matching and non-empty passwords.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private String generateRandomId() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // Add the fixed prefix
        sb.append("ETH");

        // Add random alphanumeric characters
        for (int i = sb.length(); i < 10; i++) {
            boolean isDigit = random.nextBoolean(); // Decide whether to add a digit or a letter
            if (isDigit) {
                // Add a random digit (0-9)
                sb.append(random.nextInt(10));
            } else {
                // Add a random letter (upper or lower case)
                char randomChar = (random.nextBoolean()) ?
                        (char) (random.nextInt(26) + 'A') : (char) (random.nextInt(26) + 'a');
                sb.append(randomChar);
            }
        }

        return sb.toString();
    }

    public static void main(String args[]) {
    }
}
