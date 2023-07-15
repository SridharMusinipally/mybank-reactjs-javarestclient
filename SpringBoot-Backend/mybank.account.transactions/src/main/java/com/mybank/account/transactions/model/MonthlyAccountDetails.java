package com.mybank.account.transactions.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MonthlyAccountDetails extends AccountDetails{
    private int monthlyBalance;

}
