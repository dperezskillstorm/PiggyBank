package com.piggy.bank.Data;

import com.piggy.bank.Entity.Accounts;
import com.piggy.bank.Entity.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface TransactionRepository extends MongoRepository<Transactions, String> {
    public List<Transactions> findAllByAccountNumber(String accountNumber);
}
