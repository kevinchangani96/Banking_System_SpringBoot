package com.bank.Banking.Application.Services;

import com.bank.Banking.Application.Model.Bank;
import com.bank.Banking.Application.Model.TransactionHistory;
import com.bank.Banking.Application.Model.TransferDTO;
import com.bank.Banking.Application.Repository.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class BankService {
    @Autowired
    private BankRepo bankRepo;
    @Autowired
    private TransactionService transactionService;


    public List<Bank> openingAccount(List<Bank> bank) {
        return bankRepo.saveAll(bank);
    }

    public Bank deposit(String acNumber, double deposit) {
        Bank bank = new Bank();
        bank = bankRepo.findByAcNumber(acNumber);

        TransactionHistory th = new TransactionHistory();
        th.setName(bank.getName());
        th.setTransactionId(Math.abs(generateUniqueTransactionId()));
        th.setBranchName(bank.getBranchName());
        th.setIfscCode(bank.getIfscCode());
        th.setAccountNumber(bank.getAcNumber());
        th.setAmount(deposit);
        th.setStatus("deposit");
        transactionService.add(th);
        bank.setBalance(bank.getBalance() + deposit);
        return bankRepo.save(bank);

    }

    public Bank withdraw(String acNumber, double withdraw) {
        Bank bank = new Bank();
        bank = bankRepo.findByAcNumber(acNumber);
        if (bank.getBalance() < withdraw) {
            return bank;
        }
        TransactionHistory th = new TransactionHistory();
        th.setName(bank.getName());
        th.setTransactionId(Math.abs(generateUniqueTransactionId()));
        th.setBranchName(bank.getBranchName());
        th.setIfscCode(bank.getIfscCode());
        th.setAccountNumber(bank.getAcNumber());
        th.setAmount(withdraw);
        th.setStatus("withdraw");
        transactionService.add(th);
        bank.setBalance(bank.getBalance() - withdraw);
        return bankRepo.save(bank);

    }

    public TransferDTO transfer(String sender, String reciver, double amount) {
        TransferDTO dto = new TransferDTO();


        Bank senderdetails = new Bank();
        senderdetails = bankRepo.findByAcNumber(sender);
        senderdetails.setBalance(senderdetails.getBalance() - amount);

        TransactionHistory th = new TransactionHistory();
        th.setName(senderdetails.getName());
        th.setTransactionId(Math.abs(generateUniqueTransactionId()));
        th.setBranchName(senderdetails.getBranchName());
        th.setIfscCode(senderdetails.getIfscCode());
        th.setAccountNumber(senderdetails.getAcNumber());
        th.setAmount(amount);
        th.setStatus("debited");
        transactionService.add(th);
        bankRepo.save(senderdetails);


        Bank reciverdetails = new Bank();
        reciverdetails = bankRepo.findByAcNumber(reciver);
        reciverdetails.setBalance(reciverdetails.getBalance() + amount);


        TransactionHistory th1 = new TransactionHistory();
        th1.setName(reciverdetails.getName());
        th1.setTransactionId(th.getTransactionId());
        th1.setBranchName(reciverdetails.getBranchName());
        th1.setIfscCode(reciverdetails.getIfscCode());
        th1.setAccountNumber(reciverdetails.getAcNumber());
        th1.setAmount(amount);
        th1.setStatus("credited");
        transactionService.add(th1);



        bankRepo.save(reciverdetails);

        dto.setSenderName(senderdetails.getName());
        dto.setSenderAcBalance(senderdetails.getBalance());
        dto.setReciverName(reciverdetails.getName());
        dto.setReciverAcBalance(reciverdetails.getBalance());
        return dto;

    }

    public List<Bank> getAll() {
        return bankRepo.findAll();
    }

    public Bank getByAcNumber(String acNumber) {
        return bankRepo.findByAcNumber(acNumber);
    }


    private Long generateUniqueTransactionId() {
        long timestamp = new Date().getTime();

        Random random = new Random();
        long randomNumber = random.nextLong();

        return timestamp + randomNumber;
    }
}

