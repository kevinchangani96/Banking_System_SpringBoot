package com.bank.Banking.Application.Repository;

import com.bank.Banking.Application.Model.Bank;
import com.bank.Banking.Application.Model.TransactionHistory;
import org.hibernate.query.criteria.JpaFetchParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionHistory,Long> {
    @Query(value = "SELECT * FROM transaction_history WHERE account_number =:acNumber", nativeQuery = true)
    List<TransactionHistory> findByAcNumber(String acNumber);
}
