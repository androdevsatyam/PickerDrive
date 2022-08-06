package com.model;

public class UserModel {
    String user, msg, name, phone, password, email, status;

    public UserModel(String user, String name, String phone, String email) {
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public UserModel(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getMsg() {
        return msg;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
