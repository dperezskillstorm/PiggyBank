package com.piggy.bank.Entity;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    String accountNumbers;

    ArrayList<Transactions> transactions;

    public BankAccount(String accountNumbers) {
        this.accountNumbers = accountNumbers;
    }

    public BankAccount() {

    }

    public String getAccountNumbers() {
        return accountNumbers;
    }

    public void setAccountNumbers(String accountNumbers) {
        this.accountNumbers = accountNumbers;
    }

    public ArrayList<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transactions> transactions) {
        this.transactions = transactions;
    }
}
