package com.skolli.cms.common.custom_exceptions;

import com.skolli.cms.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ApiResponse<Exception>> standardHandler(Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(exception.getMessage(), "INTERNAL_SERVER_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<ProductNotFoundException>> handleProductNotFound(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(exception.getMessage(), "PRODUCT_NOT_FOUND"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<UserNotFoundException>> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(exception.getMessage(), "USER_NOT_FOUND"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<UserNotFoundException>> handleUserCreationException(UserCreationException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(exception.getMessage(), "USER_CREATION_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<RoleNotFoundException>> handleRoleNotFoundException(RoleNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(exception.getMessage(), "ROLE_NOT_FOUND"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<ProductCreationException>> handleCreateProductException(ProductCreationException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(exception.getMessage(), "CREATE_PRODUCT_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<CategoriesNotFoundException>> handleCategoriesNotFoundException(CategoriesNotFoundException exception) {
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(exception.getMessage(),"CATEGORY_NOT_FOUND"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<ProductUpdateException>> handleProductUpdateException(ProductUpdateException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(exception.getMessage(), "PRODUCT_NOT_FOUND"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<UserDeletionException>> handleUserDeletionException(UserDeletionException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("Unable to delete the user", "USER_DELETION_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<UserUpdateException>> handleUserUpdateException(UserUpdateException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("Unable to update user", "USER_UPDATE_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<RoleCreationException>> handleRoleCreationException(RoleCreationException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(exception.getMessage(), "ROLE_CREATION_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<RoleUpdateException>> handleRoleUpdateException(RoleUpdateException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(exception.getMessage(), "ROLE_UPDATE_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<RoleDeletionException>> handleRoleDeletionException(RoleDeletionException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(exception.getMessage(), "ROLE_DELETION_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<DuplicateRoleException>> handleDuplicateRoleException(DuplicateRoleException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(exception.getMessage(), "DUPLICATE_ROLE"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<AddressNotFoundException>> handleAddressNotFoundException(AddressNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(exception.getMessage(), "ADDRESS_NOT_FOUND"));
    }

    // Payment Method Exceptions
    @ExceptionHandler
    private ResponseEntity<ApiResponse<PaymentMethodNotFoundException>> handlePaymentMethodNotFoundException(PaymentMethodNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(exception.getMessage(), "PAYMENT_METHOD_NOT_FOUND"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<PaymentMethodCreationException>> handlePaymentMethodCreationException(PaymentMethodCreationException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(exception.getMessage(), "PAYMENT_METHOD_CREATION_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<PaymentMethodUpdateException>> handlePaymentMethodUpdateException(PaymentMethodUpdateException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(exception.getMessage(), "PAYMENT_METHOD_UPDATE_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<PaymentMethodDeletionException>> handlePaymentMethodDeletionException(PaymentMethodDeletionException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(exception.getMessage(), "PAYMENT_METHOD_DELETION_ERROR"));
    }

    // Order Exceptions
    @ExceptionHandler
    private ResponseEntity<ApiResponse<OrderNotFoundException>> handleOrderNotFoundException(OrderNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(exception.getMessage(), "ORDER_NOT_FOUND"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<OrderCreationException>> handleOrderCreationException(OrderCreationException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(exception.getMessage(), "ORDER_CREATION_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<OrderUpdateException>> handleOrderUpdateException(OrderUpdateException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(exception.getMessage(), "ORDER_UPDATE_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<OrderDeletionException>> handleOrderDeletionException(OrderDeletionException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(exception.getMessage(), "ORDER_DELETION_ERROR"));
    }

    // Order Item Exceptions
    @ExceptionHandler
    private ResponseEntity<ApiResponse<OrderItemNotFoundException>> handleOrderItemNotFoundException(OrderItemNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(exception.getMessage(), "ORDER_ITEM_NOT_FOUND"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<OrderItemCreationException>> handleOrderItemCreationException(OrderItemCreationException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(exception.getMessage(), "ORDER_ITEM_CREATION_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<OrderItemUpdateException>> handleOrderItemUpdateException(OrderItemUpdateException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(exception.getMessage(), "ORDER_ITEM_UPDATE_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<OrderItemDeletionException>> handleOrderItemDeletionException(OrderItemDeletionException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(exception.getMessage(), "ORDER_ITEM_DELETION_ERROR"));
    }

    // Transaction Exceptions
    @ExceptionHandler
    private ResponseEntity<ApiResponse<TransactionNotFoundException>> handleTransactionNotFoundException(TransactionNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(exception.getMessage(), "TRANSACTION_NOT_FOUND"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<TransactionCreationException>> handleTransactionCreationException(TransactionCreationException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(exception.getMessage(), "TRANSACTION_CREATION_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<TransactionUpdateException>> handleTransactionUpdateException(TransactionUpdateException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(exception.getMessage(), "TRANSACTION_UPDATE_ERROR"));
    }

    @ExceptionHandler
    private ResponseEntity<ApiResponse<TransactionDeletionException>> handleTransactionDeletionException(TransactionDeletionException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(exception.getMessage(), "TRANSACTION_DELETION_ERROR"));
    }
}
