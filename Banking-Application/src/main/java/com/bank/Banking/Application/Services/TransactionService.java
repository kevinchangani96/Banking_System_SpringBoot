package com.bank.Banking.Application.Services;

import com.bank.Banking.Application.Model.TransactionHistory;
import com.bank.Banking.Application.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepo transactionRepo;

    public TransactionHistory add(TransactionHistory transactionHistory){
        return transactionRepo.save(transactionHistory);
    }

    public List<TransactionHistory> statment(String acNumber) {
        return transactionRepo.findByacNumber(acNumber);
    }

    public List<TransactionHistory> getAll(String acNumber) {
        return transactionRepo.findByacNumber(acNumber);

    }
}

