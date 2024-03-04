package database;

import javax.swing.*;

// User.java (in the database package)
public class Candidate {
    private String fullName;
    private String phoneNumber;
    private String age;
    private String email;
    private String randomId;
    private String password;
    private int votes;
    private String position;
    private Boolean status;
    private String remove;
    private String accountStatus;
    private String imagePath;
    public Candidate(){

    }
    public Candidate(String fullName, String phoneNumber, String age, String email, String position, String imagePath, String randomId) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
        this.position = position;
        this.randomId = randomId;
    }
    public Candidate(String fullName, String age, String position, int votes) {
        this.fullName = fullName;
        this.age = age;
        this.position = position;
        this.votes = votes;
    }
    public Candidate(String fullName, String randomId, String imagePath, String position) {
        this.fullName = fullName;
        this.randomId = randomId;
        this.imagePath = imagePath;
        this.position = position;
    }
    public Candidate(String fullName, String imagePath, String position){
        this.fullName = fullName;
        this.imagePath = imagePath;
        this.position = position;
    }
    public Candidate(String fullName) {
        this.fullName = fullName;
    }

    public Candidate(String fullName, String phoneNumber, String age, String email, String password) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
        this.password = password;
    }
    public Candidate(String fullName, String position, String imagePath, String randomId, Boolean status) {
        this.fullName = fullName;
        this.position = position;
        this.imagePath = imagePath;
        this.randomId = randomId;
        this.status = status;
    }
    public Candidate(String fullName, String phoneNumber, String age, String email, String password, String remove, String randomId, String accountStatus){
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
        this.password = password;
        this.remove = remove;
        this.randomId = randomId;
        this.accountStatus = accountStatus;
    }
    public String getRemove() {
        return remove;
    }
    public String getAccountStatus(){
        return accountStatus;
    }

    public String getEmail() {
        return email;
    }
    public String getFullName() {
        return fullName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getImagePath() {
        return imagePath;
    }
    public String getAge() {
        return age;
    }
    public String getPosition() {
        return position;
    }
    public String getRandomId() {
        return randomId;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setRandomId(String randomId) {
        this.randomId = randomId;
    }
}

