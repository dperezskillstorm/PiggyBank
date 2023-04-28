package com.piggy.bank.Service;

import com.piggy.bank.Data.AccountRepository;
import com.piggy.bank.Entity.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repo;

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
}
