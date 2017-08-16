package com.example.talaatmagdy.education;

import java.lang.ref.SoftReference;

/**
 * Created by talaatmagdy on 5/22/17.
 */

public class Users {
    private String id_user;
    private String username_user;
    private String email_user;
    private String password_users;
    private String number_users;
    private String gender_users;

    public Users()
    {

    }

    public Users(String id_user, String username_user, String email_user, String password_users, String number_users, String gender_users) {
        this.id_user = id_user;
        this.username_user = username_user;
        this.email_user = email_user;
        this.password_users = password_users;
        this.number_users = number_users;
        this.gender_users = gender_users;
    }

    public String getNumber_users() {
        return number_users;
    }

    public void setNumber_users(String number_users) {
        this.number_users = number_users;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getUsername_user() {
        return username_user;
    }

    public void setUsername_user(String username_user) {
        this.username_user = username_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getPassword_users() {
        return password_users;
    }

    public void setPassword_users(String password_users) {
        this.password_users = password_users;
    }

    public String getGender_users() {
        return gender_users;
    }

    public void setGender_users(String gender_users) {
        this.gender_users = gender_users;
    }
}
