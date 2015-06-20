package com.jdbc.sharding_db;
public class User {
    private int number;
    private String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(int number, String name) {
        super();
        this.number = number;
        this.name = name;
    }

    public User() {
        super();
    }

}
