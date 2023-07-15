package com.mybank.account.transactions.service;

import com.mybank.account.transactions.model.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

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
    RestTemplate restTemplate;

    @Value("${mybank.transactions.api.endpoint}")
    String myBankTransactionEndpoint;

//    Only for testing
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

    @Override
    public AccountDetails getMonthlyBalance(String aAccountNumber, String asOfDate) throws Exception{
        System.out.println("From AccountServiceImpl::getMonthlyBalance");
        return getBankTransactionsAndCalculate(aAccountNumber, asOfDate, "Monthly");
    }

    @Override
    public AccountDetails getCumulativeBalance(String aAccountNumber, String asOfDate) throws Exception{
        System.out.println("From AccountServiceImpl::getCumulativeBalance");
        return getBankTransactionsAndCalculate(aAccountNumber, asOfDate, "Cumulative");
    }

    /*
        Points to consume the API −
        Autowired the Rest Template Object.
        Use HttpHeaders to set the Request Headers.
        Use HttpEntity to wrap the request object.
        Provide the URL, HttpMethod, and Return type for Exchange() method.
    */
    public AccountDetails getBankTransactionsAndCalculate(String aAccountNumber, String asOfDate, String typeOfTransactions) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        //If we want to test via the stub data, then we skip the actual API call and use the test data instead.
//        if(!myBankStubTransactionsapi) {
            return restTemplate.exchange(myBankTransactionEndpoint + "/" + aAccountNumber + "/" + asOfDate, HttpMethod.GET, entity, AccountDetails.class).getBody();
//        }
//        else {
//            if (typeOfTransactions.equals("Monthly")) {
//                return getMonthlyTransactionsTestData(aAccountNumber, asOfDate);
//            } else {
//                return getCumulativeTransactionsTestData(aAccountNumber, asOfDate);
//            }
//        }
    }

//    Only for testing
//    private AccountDetails getMonthlyTransactionsTestData(String aAccountNumber, String asOfDate) throws Exception{
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
//        int monthlyBalance = accountTransactionsRpt.calculateBalance();
//
//
//        AccountDetails monthlyAccountDetails = new MonthlyAccountDetails();
//        monthlyAccountDetails.setAccountNumber(aAccountNumber);
//
//        //Up to last date of that month - showing the date entered for the sake of simplicity
//        monthlyAccountDetails.setAsOfDate(aAsOfLocalDateObj.toString());
//
//        // In Euros
//        ((MonthlyAccountDetails)monthlyAccountDetails).setMonthlyBalance(monthlyBalance);
//        return monthlyAccountDetails;
//    }
//
//    private AccountDetails getCumulativeTransactionsTestData(String aAccountNumber, String fromDate){
//
//        AccountTransactionsRpt accountTransactionsRpt = new AccountTransactionsRpt();
//        // This is the opening balance at the time from when the cumulative balance is to be calculated
//        accountTransactionsRpt.setOpeningBalance(5000);
//        accountTransactionsRpt.setAccountNumber(aAccountNumber);
//        // setting from date as the account opening date
//        accountTransactionsRpt.setFromDate(fromDate);
//
//
//        AccountDetails cumulativeAccountDetails = new CumulativeAccountDetails();
//        cumulativeAccountDetails.setAccountNumber(aAccountNumber);
//        cumulativeAccountDetails.setAsOfDate(fromDate.toString());
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
//        int cumulativeBalance = accountTransactionsRpt.calculateBalance();
//
//        // In Euros
//        ((CumulativeAccountDetails)cumulativeAccountDetails).setCumulativeBalance(cumulativeBalance);
//
//        return cumulativeAccountDetails;
//    }
}


