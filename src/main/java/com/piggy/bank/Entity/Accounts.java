package com.piggy.bank.Entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "USER_ACCOUNTS")

public class Accounts {
    @Id
    private String id;

    String firstName;

    String lastName;

    String phoneNumber;

    ArrayList<ServiceAccount> serviceAccount;


    public Accounts() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<ServiceAccount> getServiceAccount() {
        return serviceAccount;
    }

    public void setServiceAccount(ArrayList<ServiceAccount> serviceAccount) {
        this.serviceAccount = serviceAccount;
    }
}


