package com.mybank.account.transactions.service;

import com.mybank.account.transactions.model.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

@Slf4j
@Service
public class AccountTransactionsServiceImpl implements AccountTransactionsService{

    @Autowired
    RestTemplate restTemplate;

    @Value("${mybank.transactions.api.endpoint}")
    String myBankTransactionEndpoint;

    /*
    Points to consume the API −
    Autowired the Rest Template Object.
    Use HttpHeaders to set the Request Headers.
    Use HttpEntity to wrap the request object.
    Provide the URL, HttpMethod, and Return type for Exchange() method.
*/
    @Override
    public AccountTransactionsRpt getAccountTransactions(String aAccountNumber, String fromDate, String uptoDate) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<AccountTransactionsRpt> accountTransactionsRpt= restTemplate.exchange(myBankTransactionEndpoint + "/" + aAccountNumber + "/" + fromDate + "/" + uptoDate , HttpMethod.GET, entity, AccountTransactionsRpt.class);
        return accountTransactionsRpt.getBody();
    }
}


