package com.piggy.bank.Controller;

import com.piggy.bank.Entity.Accounts;
import com.piggy.bank.Entity.ServiceAccount;
import com.piggy.bank.Entity.Transactions;
import com.piggy.bank.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @PatchMapping("/addAccount/{firstName}")
//    {
//        "accountNumbers": "6666"
//    }
    public ResponseEntity<Accounts> associateBankAccountWithUser(@PathVariable String firstName,@RequestBody ServiceAccount serviceAccount){
        Accounts accountToUpdate = service.findByFirstName(firstName);
        ServiceAccount acct1 = new ServiceAccount();
        acct1.setAccountNumbers(serviceAccount.getAccountNumbers());
        acct1.setInterestRate(serviceAccount.getInterestRate());
        acct1.setPeriods(serviceAccount.getPeriods());
        acct1.setLoanAmount(serviceAccount.getLoanAmount());


        ArrayList<ServiceAccount> arrService = accountToUpdate.getServiceAccount();
        if(arrService == null){
            arrService = new ArrayList<>();
        }
        arrService.add(acct1);

        accountToUpdate.setServiceAccount(arrService);
        Accounts response = service.associateBankAccountWithUser(accountToUpdate);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/addTransactions/{firstName}/{account}")
//    {
//    "accountNumber": "9999"
//        "amount": 900.0,
//            "date": "04/26/2023",
//            "description": "Some Home Stuff",
//            "category": "Expense"
//    }

    public ResponseEntity<Transactions> addTransaction(@PathVariable String firstName,@PathVariable String account, @RequestBody Transactions transactions){
        Transactions trans = new Transactions();
        trans.setAccountNumber(account);
    LocalDate today = LocalDate.now();
        trans.setDate(today.toString());
        trans.setAmount(transactions.getAmount());
        trans.setDescription(transactions.getDescription());
        trans.setCategory(transactions.getCategory());
        Transactions response = service.createTransaction(trans);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/balance/{firstName}/{account}")
    public Double getBalance(@PathVariable String firstName, @PathVariable String account){
         List<Transactions> result = service.findTransactionsByAccountNumber(account);
         Double totalBalance = result.stream().mapToDouble(tran-> tran.getAmount()).sum();
         return totalBalance;
    }






}
