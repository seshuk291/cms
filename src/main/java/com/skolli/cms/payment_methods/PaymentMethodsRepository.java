package com.skolli.cms.payment_methods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMethodsRepository extends JpaRepository<PaymentMethods, Long> {
    List<PaymentMethods> findByUserId(Long userId);
    List<PaymentMethods> findByPaymentType(String paymentType);
    List<PaymentMethods> findByPaymentProcessor(String paymentProcessor);
}