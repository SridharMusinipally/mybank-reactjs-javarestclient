package com.mybank.account.transactions.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

@Setter
@Getter
public class AccountTransactionsRpt {

    // This could be the opening balance of the month or the initial balance from when the balance is to be calculated based on the type of API being accessed.
    int openingBalance = 0;

    private String accountNumber;

    // This will be 1st of the month for which the balance is requested in case of monthly Balance
    // This will be account opening date from which the balance is requested in case of cumulative Balance
    private String fromDate;

    private String upToDate;

    List<AccountTransaction> accountTransactionsList = new ArrayList<AccountTransaction>();

    public int calculateBalance(){
        int calculatedBalance = 0;
        calculatedBalance = openingBalance;
        Iterator iterator = accountTransactionsList.iterator();
        AccountTransaction accountTransaction = null;
        while(iterator.hasNext()){
            accountTransaction = (AccountTransaction)iterator.next();
            if(accountTransaction.getTransactionType().equals("CR")){
                calculatedBalance += accountTransaction.getTransactionAmount();
            } else {
                calculatedBalance -= accountTransaction.getTransactionAmount();
            }
        }

        return calculatedBalance;
    }
}
