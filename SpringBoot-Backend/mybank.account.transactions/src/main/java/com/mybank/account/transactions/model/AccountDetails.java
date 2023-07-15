package com.mybank.account.transactions.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public abstract class AccountDetails {
    private String accountNumber;

    private String asOfDate;
}
