package com.drayy.entity;

public class UserEntity {
    private String username;
    private String password;
    private String sex;

    public UserEntity() {
    }

    public UserEntity(String username, String password, String sex) {
        this.username = username;
        this.password = password;
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "username="+username+"&password="+password +"&sex="+sex;
    }
}
