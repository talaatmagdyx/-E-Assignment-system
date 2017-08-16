package com.example.talaatmagdy.education;

/**
 * Created by talaatmagdy on 5/22/17.
 */

public class retrieve_user {
    private String username;
    private String email;
    private String password;
    private String phonenumber;
    private String Gender;

    public retrieve_user(String username, String email, String password, String phonenumber, String gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        Gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
