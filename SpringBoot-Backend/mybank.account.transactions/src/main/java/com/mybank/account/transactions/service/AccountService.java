package com.mybank.account.transactions.service;

import com.mybank.account.transactions.model.AccountDetails;
import com.mybank.account.transactions.model.AccountTransactionsRpt;

import java.util.Date;

public interface AccountService {
    public AccountDetails getMonthlyBalance(String aAccountNumber, String asOfDate) throws Exception;
    public AccountDetails getCumulativeBalance(String aAccountNumber, String fromDate) throws Exception;
}
