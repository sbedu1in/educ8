package com.solvd.educ8.demo.utils;

public class UserData {

    private String email;

    private String password;

    public UserData(String email, String password){
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getMailPassword() {
        return password;
    }
}
