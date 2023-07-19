package com.mybank.account.transactions.controller;

import com.mybank.account.transactions.model.CumulativeAccountDetails;
import com.mybank.account.transactions.model.MonthlyAccountDetails;
import com.mybank.account.transactions.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "https://localhost:3000/", maxAge = 3600)
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/api/account/getmonthlybalance/{accountID}/{asOfDate}")
    public ResponseEntity<MonthlyAccountDetails>  getMonthlyBalance(@PathVariable String accountID, @PathVariable String asOfDate) throws Exception{
        MonthlyAccountDetails monthlyAccountDetails = null;

        log.info("Beginning of AccountController::getMonthlyBalance Date received:" + asOfDate);
//        Date aAsOfDate=new SimpleDateFormat("dd-MM-yyyy").parse(asOfDate);

        try {
            // Should not log aAccountNumber as it is PII data
            log.info("Beginning of AccountController::getMonthlyBalance for accountID:" + accountID);
            monthlyAccountDetails = (MonthlyAccountDetails) accountService.getMonthlyBalance(accountID, asOfDate);
            return new ResponseEntity(monthlyAccountDetails, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            log.error("Exception encountered in AccountController::getMonthlyBalance():", e.getClass().getName(), e.getMessage());
            return new ResponseEntity<>(monthlyAccountDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/account/getcumulativebalance/{accountID}/{fromDate}")
    public ResponseEntity<CumulativeAccountDetails> getCumulativeBalance(@PathVariable String accountID, @PathVariable String fromDate) throws Exception{
        CumulativeAccountDetails cumulativeAccountDetails = null;

        log.info("Beginning of AccountController::getCumulativeBalance Date received:" + fromDate);
//        Date aAsOfDate=new SimpleDateFormat("dd-MM-yyyy").parse(asOfDate);

        try {
            // Should not log aAccountNumber as it is PII data
            log.info("Beginning of AccountController::getCumulativeBalance for accountID:" + accountID);
            cumulativeAccountDetails = (CumulativeAccountDetails) accountService.getCumulativeBalance(accountID, fromDate);
            System.out.println("From getCumulativeBalance");
            return new ResponseEntity(cumulativeAccountDetails, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            log.error("Exception encountered in AccountController::getCumulativeBalance():", e.getClass().getName(), e.getMessage());
            return new ResponseEntity<>(cumulativeAccountDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
