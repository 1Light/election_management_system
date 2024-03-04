package database;

// User.java (in the database package)
public class Voter {
    private String fullName;
    private String phoneNumber;
    private String age;
    private String email;
    private String password;
    private boolean voteStatus;
    private String position;
    private String randomId;
    private String remove;
    private String accountStatus;

    public Voter(String fullName, String phoneNumber, String age, String email, String randomId, String password) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
        this.randomId = randomId;
        this.password = password;
    }
    public Voter(String fullName, String phoneNumber, String age, String email, String password, Boolean voteStatus) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
        this.password = password;
        this.voteStatus = voteStatus;
    }
    public Voter(String fullName, String phoneNumber, String age, String email, String password, boolean voteStatus, String remove, String randomId, String accountStatus){
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
        this.password = password;
        this.voteStatus = voteStatus;
        this.remove = remove;
        this.randomId = randomId;
        this.accountStatus = accountStatus;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getAge() {
        return age;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getFullName() {
        return fullName;
    }
    public Boolean getVoteStatus() {
        return voteStatus;
    }
    public String getRemove() {
        return remove;
    }
    public String getRandomId(){
        return randomId;
    }
    public String getAccountStatus(){
        return accountStatus;
    }
    public String getPosition() {
        return position;
    }
}

