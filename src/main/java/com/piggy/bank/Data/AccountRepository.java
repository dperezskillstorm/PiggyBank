package com.piggy.bank.Data;

import com.piggy.bank.Entity.Accounts;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface AccountRepository extends MongoRepository<Accounts, String> {

    public Accounts findByFirstName(String firstName);
}
