package com.piggy.bank.Service;

import com.piggy.bank.Data.AccountRepository;
import com.piggy.bank.Data.TransactionRepository;
import com.piggy.bank.Entity.Accounts;
import com.piggy.bank.Entity.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repo;

    @Autowired
    private TransactionRepository transactionRepository;

    public Accounts save(Accounts account){
        return repo.save(account);
    }

    public List<Accounts> findAll(String firstName){
        return repo.findAll();
    }

    public Accounts findByFirstName(String firstName){
        return repo.findByFirstName(firstName);
    }

    public Accounts associateBankAccountWithUser(Accounts accountToUpdate) {
        return repo.save(accountToUpdate);
    }

    public Transactions createTransaction(Transactions trans) {
        return transactionRepository.save(trans);
    }

    public List<Transactions> findTransactionsByAccountNumber(String accountNumber){
        return transactionRepository.findAllByAccountNumber(accountNumber);
    }
}
