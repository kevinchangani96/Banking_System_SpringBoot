package com.bank.Banking.Application.Controller;

import com.bank.Banking.Application.Model.TransactionHistory;
import com.bank.Banking.Application.Services.EmailService;
import com.bank.Banking.Application.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("bankpassbook")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    EmailService emailService;

    @GetMapping("/statement")
    public List<TransactionHistory> statment(@RequestParam String acNumber) {
        return transactionService.statment(acNumber);
    }

    @GetMapping("/statementEmail")
    public List<TransactionHistory> statmentEmail(@RequestParam String acNumber, String email) {
        List<TransactionHistory> list = transactionService.getAll(acNumber);
        emailService.sendTransactionHistoryEmail(email, list);
        return list;
    }

}
