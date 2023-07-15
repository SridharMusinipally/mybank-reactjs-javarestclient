package com.mybank.account.transactions.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CumulativeAccountDetails extends AccountDetails{
    private int cumulativeBalance;
}
