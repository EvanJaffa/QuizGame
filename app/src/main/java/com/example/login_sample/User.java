package com.example.login_sample;

public class User {
    String firstName, lastName, email, password;
    int dob;

    public User(String firstName, String lastName, int dob, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }
    public User(String email, String password){
        this.email = email;
        this.password = password;
        this.dob = -1;
        this.firstName = "";
        this.lastName = "";
    }
}
