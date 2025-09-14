package com.skolli.cms.transactions;

import com.skolli.cms.transactions.dto.CreateTransactionDto;
import com.skolli.cms.transactions.dto.TransactionItemDto;
import com.skolli.cms.transactions.dto.UpdateTransactionDto;
import com.skolli.cms.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionsService transactionsService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TransactionItemDto>>> getAllTransactions() {
        List<TransactionItemDto> transactions = this.transactionsService.findAllTransactions();
        return ResponseEntity.ok(ApiResponse.success("Transactions retrieved successfully", transactions));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<ApiResponse<TransactionItemDto>> getTransactionById(@PathVariable Long transactionId) {
        TransactionItemDto transaction = this.transactionsService.getTransactionById(transactionId);
        return ResponseEntity.ok(ApiResponse.success("Transaction retrieved successfully", transaction));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<TransactionItemDto>>> getTransactionsByStatus(@PathVariable Boolean status) {
        List<TransactionItemDto> transactions = this.transactionsService.getTransactionsByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Transactions by status retrieved successfully", transactions));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<TransactionItemDto>>> getTransactionsByUserId(@PathVariable Long userId) {
        List<TransactionItemDto> transactions = this.transactionsService.getTransactionsByUserId(userId);
        return ResponseEntity.ok(ApiResponse.success("User transactions retrieved successfully", transactions));
    }

    @GetMapping("/successful")
    public ResponseEntity<ApiResponse<List<TransactionItemDto>>> getSuccessfulTransactions() {
        List<TransactionItemDto> transactions = this.transactionsService.getSuccessfulTransactions();
        return ResponseEntity.ok(ApiResponse.success("Successful transactions retrieved successfully", transactions));
    }

    @GetMapping("/failed")
    public ResponseEntity<ApiResponse<List<TransactionItemDto>>> getFailedTransactions() {
        List<TransactionItemDto> transactions = this.transactionsService.getFailedTransactions();
        return ResponseEntity.ok(ApiResponse.success("Failed transactions retrieved successfully", transactions));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TransactionItemDto>> createTransaction(@RequestBody CreateTransactionDto transactionDto) {
        TransactionItemDto createdTransaction = this.transactionsService.createTransaction(transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Transaction created successfully", createdTransaction));
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<ApiResponse<Boolean>> updateTransaction(
            @PathVariable Long transactionId,
            @RequestBody UpdateTransactionDto transactionDto
    ) {
        Boolean updated = this.transactionsService.updateTransaction(transactionId, transactionDto);
        return ResponseEntity.ok(ApiResponse.success("Transaction updated successfully", updated));
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<ApiResponse<Boolean>> deleteTransaction(@PathVariable Long transactionId) {
        Boolean deleted = this.transactionsService.deleteTransaction(transactionId);
        return ResponseEntity.ok(ApiResponse.success("Transaction deleted successfully", deleted));
    }
}