package com.mybank.account.transactions.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
public class Error {
    String errorDescription = "";
    String errorCode = "";
}
