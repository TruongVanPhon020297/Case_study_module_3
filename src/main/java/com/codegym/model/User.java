package com.codegym.model;

public class User {
    private int id;
    private String fullName;
    private String mobile;
    private String email;
    private String passwordUser;
    private String registeredAt;
    private String updatedAt;
    private int admin;
    private int status;
    private String urlImage;

    public User(int id, String fullName, String mobile, String email, String passwordUser, String registeredAt, String updatedAt, int admin, int status, String urlImage) {
        this.id = id;
        this.fullName = fullName;
        this.mobile = mobile;
        this.email = email;
        this.passwordUser = passwordUser;
        this.registeredAt = registeredAt;
        this.updatedAt = updatedAt;
        this.admin = admin;
        this.status = status;
        this.urlImage = urlImage;
    }

    public User(String fullName, String mobile, String email, String passwordUser, int admin, int status, String urlImage) {
        this.fullName = fullName;
        this.mobile = mobile;
        this.email = email;
        this.passwordUser = passwordUser;
        this.admin = admin;
        this.status = status;
        this.urlImage = urlImage;
    }

    public User(String fullName, String mobile, String email, String registeredAt, String updatedAt, int admin, int status, String urlImage) {
        this.fullName = fullName;
        this.mobile = mobile;
        this.email = email;
        this.registeredAt = registeredAt;
        this.updatedAt = updatedAt;
        this.admin = admin;
        this.status = status;
        this.urlImage = urlImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(String registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
