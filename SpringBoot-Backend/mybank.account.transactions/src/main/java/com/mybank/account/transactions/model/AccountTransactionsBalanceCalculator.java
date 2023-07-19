package com.mybank.account.transactions.model;

import java.util.Iterator;

public class AccountTransactionsBalanceCalculator extends AccountTransactionsRpt{

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
