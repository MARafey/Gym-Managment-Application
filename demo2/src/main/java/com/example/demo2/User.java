package com.example.demo2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    public String name;
    public String email;
    public String role;
    public String password;
    public String cnic;
    public String phoneNumber;

    public User(String name, String email, String role, String password, String cnic, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
        this.cnic = cnic;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters for the class
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public StringProperty nameProperty() {
        StringProperty na= new SimpleStringProperty(name);
        return na;
    }

    public StringProperty emailProperty() {
        StringProperty em= new SimpleStringProperty(email);
        return em;
    }

    public StringProperty cnicProperty() {
        StringProperty cn= new SimpleStringProperty(cnic);
        return cn;
    }

    public StringProperty pnProperty() {
        StringProperty pn= new SimpleStringProperty(phoneNumber);
        return pn;
    }
}
