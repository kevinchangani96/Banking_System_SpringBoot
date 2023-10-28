package com.bank.Banking.Application.Repository;

import com.bank.Banking.Application.Model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepo extends JpaRepository<Bank,Long> {
//    @Query(value = "SELECT * FROM bank WHERE ac_number =:acNumber", nativeQuery = true)
//    Bank findByAcNumber(String acNumber);

    Bank findByAcNumber(String acNumber);
}
