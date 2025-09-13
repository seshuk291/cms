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
}
