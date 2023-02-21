package com.development.models;


public class UsersModel {
    private String token;
    private String email;

    private String name;

    private String avatar;

    private String avatarUrl;

    private String password;

    private String role;

    private boolean isAdmin;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public UsersModel() {

    }

    public UsersModel(String token, String email, String name, String avatar, String avatarUrl, String password, String role, boolean isAdmin) {
        this.token = token;
        this.email = email;
        this.name = name;
        this.avatar = avatar;
        this.avatarUrl = avatarUrl;
        this.password = password;
        this.role = role;
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "UsersModel{" +
                "token='" + token + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
