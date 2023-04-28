package com.piggy.bank.Controller;

import com.piggy.bank.Entity.Accounts;
import com.piggy.bank.Entity.BankAccount;
import com.piggy.bank.Entity.Transactions;
import com.piggy.bank.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("piggy/v1")
public class AccountControllers {
    @Autowired
    AccountService service;

    @PostMapping("/openNewAccount")
//    {
//        "firstName" : "David",
//            "lastName" : "Perez",
//            "phoneNumber": "956-926-9082"
//
//    }
    public ResponseEntity<Accounts> createUserAccount(@RequestBody Accounts accounts){
        Accounts newAccount = new Accounts();
        newAccount.setFirstName(accounts.getFirstName());
        newAccount.setLastName(accounts.getLastName());
        newAccount.setPhoneNumber(accounts.getPhoneNumber());
        Accounts response = service.save(newAccount);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/addBankAccountNumber/{firstName}")
//    {
//        "accountNumbers": "6666"
//    }
    public ResponseEntity<Accounts> associateBankAccountWithUser(@PathVariable String firstName,@RequestBody BankAccount bankAccount){
        Accounts accountToUpdate = service.findByFirstName(firstName);
        BankAccount acct1 = new BankAccount();
        acct1.setAccountNumbers(bankAccount.getAccountNumbers());

        accountToUpdate.setBankAccount(acct1);
        Accounts response = service.associateBankAccountWithUser(accountToUpdate);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/addTransactions/{firstName}/{account}")
//    {
//        "amount": 900.0,
//            "date": "04/26/2023",
//            "description": "Some Home Stuff",
//            "category": "Expense"
//    }

    public ResponseEntity<Accounts> addTransaction(@PathVariable String firstName,@PathVariable String account, @RequestBody Transactions transactions){
        Accounts accountToUpdate = service.findByFirstName(firstName);
        BankAccount acct1 = new BankAccount();

        acct1.setAccountNumbers(accountToUpdate.getBankAccount().getAccountNumbers());
        acct1.setTransactions(accountToUpdate.getBankAccount().getTransactions());
        ArrayList<Transactions> lsOfTransactions = acct1.getTransactions();
        System.out.println(lsOfTransactions);
        if (lsOfTransactions == null){
            lsOfTransactions = new ArrayList<Transactions>();

        }
        lsOfTransactions.add(new Transactions(transactions.getAmount(),transactions.getDate(),transactions.getDescription(),transactions.getCategory()));
        acct1.setTransactions(lsOfTransactions);
        System.out.println("TRANSACTIONS ARRAY Before: " + lsOfTransactions);


        accountToUpdate.setBankAccount(acct1);
        Accounts response = service.associateBankAccountWithUser(accountToUpdate);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/balance/{firstName}/{account}")
    public Double getBalance(@PathVariable String firstName, @PathVariable String account){
         Accounts result = service.findByFirstName(firstName);
         Double totalBalance = result.getBankAccount().getTransactions().stream().mapToDouble(tran-> tran.getAmount()).sum();
         return totalBalance;
    }






}
