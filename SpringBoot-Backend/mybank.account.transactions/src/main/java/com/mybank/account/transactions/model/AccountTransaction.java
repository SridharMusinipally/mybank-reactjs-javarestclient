package com.mybank.account.transactions.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountTransaction {

    String transactionType = null;
    int transactionAmount = 0;

}
