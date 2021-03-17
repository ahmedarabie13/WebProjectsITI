package com.playground.model;

public class User{
    private String username;
    private String gender;
    private Integer id;

    public User(String username, String gender, Integer id) {
        this.username = username;
        this.gender = gender;
        this.id = id;
    }

    public User( ) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                '}';
    }
}