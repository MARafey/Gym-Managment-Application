package com.example.demo2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Toadd {
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty cnic;
    private final StringProperty Pn;

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getCnic() {
        return cnic.get();
    }

    public StringProperty cnicProperty() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic.set(cnic);
    }

    public String getPn() {
        return Pn.get();
    }

    public StringProperty pnProperty() {
        return Pn;
    }

    public void setPn(String pn) {
        this.Pn.set(pn);
    }

    public Toadd(StringProperty name, StringProperty email, StringProperty cnic, StringProperty pn) {
        this.name = name;
        this.email = email;
        this.cnic = cnic;
        Pn = pn;
    }

    public Toadd(String name, String email, String cnic, String phoneNumber) {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.cnic = new SimpleStringProperty(cnic);
        Pn = new SimpleStringProperty(phoneNumber);

    }

    public String getname() {
        return name.get();
    }

    public String getemail() {
        return email.get();
    }

    public String getcnic() {
        return cnic.get();
    }

    public String getphoneNumber() {
        return Pn.get();
    }

    public ObservableValue<String> phoneNumberProperty() {
        return Pn;
    }
}
