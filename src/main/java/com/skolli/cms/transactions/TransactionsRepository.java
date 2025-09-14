package com.skolli.cms.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findByStatus(Boolean status);
    List<Transactions> findByPaymentMethodId(Long paymentMethodId);
    
    @Query("SELECT t FROM Transactions t WHERE t.paymentMethod.user.id = :userId")
    List<Transactions> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT t FROM Transactions t WHERE t.status = true")
    List<Transactions> findAllSuccessfulTransactions();
    
    @Query("SELECT t FROM Transactions t WHERE t.status = false")
    List<Transactions> findAllFailedTransactions();
}