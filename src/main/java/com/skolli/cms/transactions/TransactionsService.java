package com.skolli.cms.transactions;

import com.skolli.cms.common.custom_exceptions.*;
import com.skolli.cms.payment_methods.PaymentMethods;
import com.skolli.cms.payment_methods.PaymentMethodsRepository;
import com.skolli.cms.transactions.dto.CreateTransactionDto;
import com.skolli.cms.transactions.dto.TransactionItemDto;
import com.skolli.cms.transactions.dto.UpdateTransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionsService {
    
    private final TransactionsRepository transactionsRepository;
    private final PaymentMethodsRepository paymentMethodsRepository;

    public List<TransactionItemDto> findAllTransactions() {
        return this.transactionsRepository.findAll()
                .stream()
                .map(this::mapTransactionToDto)
                .collect(Collectors.toList());
    }

    public TransactionItemDto getTransactionById(Long transactionId) {
        Transactions transaction = this.transactionsRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + transactionId));
        return mapTransactionToDto(transaction);
    }

    public List<TransactionItemDto> getTransactionsByStatus(Boolean status) {
        return this.transactionsRepository.findByStatus(status)
                .stream()
                .map(this::mapTransactionToDto)
                .collect(Collectors.toList());
    }

    public List<TransactionItemDto> getTransactionsByUserId(Long userId) {
        return this.transactionsRepository.findByUserId(userId)
                .stream()
                .map(this::mapTransactionToDto)
                .collect(Collectors.toList());
    }

    public List<TransactionItemDto> getSuccessfulTransactions() {
        return this.transactionsRepository.findAllSuccessfulTransactions()
                .stream()
                .map(this::mapTransactionToDto)
                .collect(Collectors.toList());
    }

    public List<TransactionItemDto> getFailedTransactions() {
        return this.transactionsRepository.findAllFailedTransactions()
                .stream()
                .map(this::mapTransactionToDto)
                .collect(Collectors.toList());
    }

    public TransactionItemDto createTransaction(CreateTransactionDto transactionDto) {
        PaymentMethods paymentMethod = this.paymentMethodsRepository.findById(transactionDto.getPaymentMethodId())
                .orElseThrow(() -> new PaymentMethodNotFoundException("Payment method not found with id: " + transactionDto.getPaymentMethodId()));
        
        try {
            Transactions transaction = new Transactions();
            transaction.setStatus(transactionDto.getStatus());
            transaction.setTotalAmount(transactionDto.getTotalAmount());
            transaction.setPaymentMethod(paymentMethod);
            
            Transactions savedTransaction = this.transactionsRepository.save(transaction);
            return mapTransactionToDto(savedTransaction);
        } catch (Exception exception) {
            throw new TransactionCreationException("Unable to create transaction: " + exception.getMessage());
        }
    }

    public Boolean updateTransaction(Long transactionId, UpdateTransactionDto transactionDto) {
        Transactions transaction = this.transactionsRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + transactionId));
        
        try {
            transaction.setStatus(transactionDto.getStatus());
            transaction.setTotalAmount(transactionDto.getTotalAmount());
            this.transactionsRepository.save(transaction);
            return true;
        } catch (Exception exception) {
            throw new TransactionUpdateException("Unable to update transaction: " + exception.getMessage());
        }
    }

    public Boolean deleteTransaction(Long transactionId) {
        Transactions transaction = this.transactionsRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + transactionId));
        
        // Check if transaction has associated order
        if (transaction.getOrder() != null) {
            throw new TransactionDeletionException("Cannot delete transaction with associated order");
        }
        
        try {
            this.transactionsRepository.deleteById(transactionId);
            return true;
        } catch (Exception exception) {
            throw new TransactionDeletionException("Unable to delete transaction: " + exception.getMessage());
        }
    }

    private TransactionItemDto mapTransactionToDto(Transactions transaction) {
        return TransactionItemDto.builder()
                .id(transaction.getId())
                .status(transaction.getStatus())
                .totalAmount(transaction.getTotalAmount())
                .paymentMethodId(transaction.getPaymentMethod().getId())
                .paymentType(transaction.getPaymentMethod().getPaymentType())
                .paymentProcessor(transaction.getPaymentMethod().getPaymentProcessor())
                .orderId(transaction.getOrder() != null ? transaction.getOrder().getId() : null)
                .build();
    }
}