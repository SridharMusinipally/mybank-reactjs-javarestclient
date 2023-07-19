package com.mybank.account.transactions.service;

import com.mybank.account.transactions.model.AccountTransactionsRpt;

public interface AccountTransactionsService {
    public AccountTransactionsRpt getAccountTransactions(String aAccountNumber, String fromDate, String uptoDate) throws Exception;
}
