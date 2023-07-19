package com.mybank.account.transactions.service;

import com.mybank.account.transactions.model.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountTransactionsService accountTransactionsService;

    //    Only for testing with stub data
//    @Value("${mybank.stub.transactionsapi}")
//    boolean myBankStubTransactionsapi;

//    @PostConstruct
//    public void test() throws Exception{
//        int monthlyBalance = ((MonthlyAccountDetails)getMonthlyBalance("007", "20-06-1977")).getMonthlyBalance();
//        System.out.println("Monthly balance:" + monthlyBalance);
//
//        int cumulativeBalance = ((CumulativeAccountDetails)getCumulativeBalance("007", "20-06-1977")).getCumulativeBalance();
//        System.out.println("Cumulative balance:" + cumulativeBalance);
//    }

    private AccountTransactionsBalanceCalculator loadTransactionsResponse(AccountTransactionsRpt accountTransactionsRpt){
        AccountTransactionsBalanceCalculator accountTransactionsBalanceCalculator = new AccountTransactionsBalanceCalculator();
        accountTransactionsBalanceCalculator.setAccountNumber(accountTransactionsRpt.getAccountNumber());
        accountTransactionsBalanceCalculator.setOpeningBalance(accountTransactionsRpt.getOpeningBalance());
        accountTransactionsBalanceCalculator.setFromDate(accountTransactionsRpt.getFromDate());
        accountTransactionsBalanceCalculator.setUpToDate(accountTransactionsRpt.getUpToDate());
        accountTransactionsBalanceCalculator.setAccountTransactionsList(accountTransactionsRpt.getAccountTransactionsList());
        return accountTransactionsBalanceCalculator;
    }



    @Override
    public AccountDetails getMonthlyBalance(String aAccountNumber, String asOfDate) throws Exception{
        System.out.println("From AccountServiceImpl::getMonthlyBalance");
        Date aAsOfDateObj=new SimpleDateFormat("dd-MM-yyyy").parse(asOfDate);

        LocalDate aAsOfLocalDateObj = aAsOfDateObj.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate firstOfThatMonth = aAsOfLocalDateObj.withDayOfMonth(1);
        AccountTransactionsRpt accountTransactionsRpt = null;

//    Only for testing with stub data
//if(myBankStubTransactionsapi) {
//    accountTransactionsRpt = getMonthlyTransactionsTestData(aAccountNumber, asOfDate);
//}else {
    accountTransactionsRpt = accountTransactionsService.getAccountTransactions(aAccountNumber, firstOfThatMonth.toString(), asOfDate);
//}

        AccountTransactionsBalanceCalculator accountTransactionsBalanceCalculator = loadTransactionsResponse(accountTransactionsRpt);

        AccountDetails monthlyAccountDetails = new MonthlyAccountDetails();
        monthlyAccountDetails.setAccountNumber(aAccountNumber);
        monthlyAccountDetails.setAsOfDate(aAsOfLocalDateObj.toString());

        // In Euros
        ((MonthlyAccountDetails)monthlyAccountDetails).setMonthlyBalance(accountTransactionsBalanceCalculator.calculateBalance());

        return monthlyAccountDetails;
    }

    @Override
    public AccountDetails getCumulativeBalance(String aAccountNumber, String fromDate) throws Exception{
        System.out.println("From AccountServiceImpl::getCumulativeBalance");
        AccountTransactionsRpt accountTransactionsRpt = null;

//    Only for testing with stub data
//if(myBankStubTransactionsapi) {
//    accountTransactionsRpt = getCumulativeTransactionsTestData(aAccountNumber, fromDate);
//}else {
    accountTransactionsRpt = accountTransactionsService.getAccountTransactions(aAccountNumber, fromDate.toString(), new Date().toString());
//}

        AccountTransactionsBalanceCalculator accountTransactionsBalanceCalculator = loadTransactionsResponse(accountTransactionsRpt);

        AccountDetails cumulativeAccountDetails = new CumulativeAccountDetails();
        cumulativeAccountDetails.setAccountNumber(aAccountNumber);
        cumulativeAccountDetails.setAsOfDate(fromDate.toString());

        // In Euros
        ((CumulativeAccountDetails)cumulativeAccountDetails).setCumulativeBalance((accountTransactionsBalanceCalculator.calculateBalance()));
        return cumulativeAccountDetails;
    }



    //    Only for testing with stub data
//    private AccountTransactionsRpt getMonthlyTransactionsTestData(String aAccountNumber, String asOfDate) throws Exception{
//
//        AccountTransactionsRpt accountTransactionsRpt = new AccountTransactionsRpt();
//        // This is the opening balance at the beginning of that month
//        accountTransactionsRpt.setOpeningBalance(5000);
//        accountTransactionsRpt.setAccountNumber(aAccountNumber);
//
//        Date aAsOfDateObj=new SimpleDateFormat("dd-MM-yyyy").parse(asOfDate);
//
//        LocalDate aAsOfLocalDateObj = aAsOfDateObj.toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate();
//
//        LocalDate firstOfThatMonth = aAsOfLocalDateObj.withDayOfMonth(1);
//
//        accountTransactionsRpt.setFromDate(firstOfThatMonth.toString());
//        accountTransactionsRpt.setUpToDate(asOfDate);
//
//        // load test transactions which would ideally come from the 3rd party API
//        List<AccountTransaction> accountTransactionsList = new ArrayList<AccountTransaction>();
//        AccountTransaction accountTransaction = new AccountTransaction();
//        accountTransaction.setTransactionType("CR");
//        accountTransaction.setTransactionAmount(5000);
//        accountTransactionsList.add(accountTransaction);
//
//        AccountTransaction accountTransaction2 = new AccountTransaction();
//        accountTransaction2.setTransactionType("DR");
//        accountTransaction2.setTransactionAmount(10000);
//        accountTransactionsList.add(accountTransaction2);
//
//        AccountTransaction accountTransaction3 = new AccountTransaction();
//        accountTransaction3.setTransactionType("CR");
//        accountTransaction3.setTransactionAmount(30000);
//        accountTransactionsList.add(accountTransaction3);
//
//        accountTransactionsRpt.setAccountTransactionsList(accountTransactionsList);
//        return accountTransactionsRpt;
//    }

    //    Only for testing with stub data
//    private AccountTransactionsRpt getCumulativeTransactionsTestData(String aAccountNumber, String fromDate){
//
//        AccountTransactionsRpt accountTransactionsRpt = new AccountTransactionsRpt();
//        // This is the opening balance at the time from when the cumulative balance is to be calculated
//        accountTransactionsRpt.setOpeningBalance(5000);
//        accountTransactionsRpt.setAccountNumber(aAccountNumber);
//        // setting from date as the account opening date
//        accountTransactionsRpt.setFromDate(fromDate);
//        accountTransactionsRpt.setUpToDate(new Date().toString());
//
//        // load test transactions which would ideally come from the 3rd party API
//        List<AccountTransaction> accountTransactionsList = new ArrayList<AccountTransaction>();
//        AccountTransaction accountTransaction = new AccountTransaction();
//        accountTransaction.setTransactionType("CR");
//        accountTransaction.setTransactionAmount(5000);
//        accountTransactionsList.add(accountTransaction);
//
//        AccountTransaction accountTransaction2 = new AccountTransaction();
//        accountTransaction2.setTransactionType("DR");
//        accountTransaction2.setTransactionAmount(10000);
//        accountTransactionsList.add(accountTransaction2);
//
//        AccountTransaction accountTransaction3 = new AccountTransaction();
//        accountTransaction3.setTransactionType("CR");
//        accountTransaction3.setTransactionAmount(60000);
//        accountTransactionsList.add(accountTransaction3);
//        accountTransactionsRpt.setAccountTransactionsList(accountTransactionsList);
//
//        return accountTransactionsRpt;
//    }
}


