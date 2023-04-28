package com.piggy.bank.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TRANSACTIONS")
@Data
public class Transactions {
    @Id
    private String Id;

    private String accountNumber;
    private double amount;
    private String date;
    private String description;
    private String category;


}
