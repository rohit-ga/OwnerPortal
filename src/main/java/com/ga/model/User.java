package com.ga.model;

public class User {

    private int userId;
    private String userFirstName;
    private String userLastName;
    private String userGender;
    private Long userContact;
    private String userEmail;
    private String userPassword;

    public User(String userFirstName, String userLastName, String userGender, Long contact, String userEmail,
            String userPassword) {
        super();

        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userGender = userGender;
        this.userContact = contact;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Long getUserContact() {
        return userContact;
    }

    public void setUserContact(Long userContact) {
        this.userContact = userContact;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
                + ", userGender=" + userGender + ", userContact=" + userContact + ", userEmail=" + userEmail
                + ", userPassword=" + userPassword + "]";
    }

}
