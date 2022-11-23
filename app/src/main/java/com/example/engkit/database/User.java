package com.example.engkit.database;


import org.threeten.bp.LocalDate;

public class User {

    private int id;
    private String email;
    private String code;


    private String password;
    private LocalDate dateOfBirth;
    private String name;


    public User(int id, String email, String code, String password, LocalDate dateOfBirth, String name) {
        this.id = id;
        this.email = email;
        this.code = code;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
