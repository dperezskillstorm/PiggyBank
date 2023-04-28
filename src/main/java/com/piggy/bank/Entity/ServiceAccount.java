package com.piggy.bank.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
public class ServiceAccount {

    String accountNumbers;

    Double interestRate;

    Double loanAmount;

    int periods;

    double payoffMin;




}
