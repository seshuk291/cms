package com.skolli.cms.payment_methods;

import com.skolli.cms.payment_methods.dto.CreatePaymentMethodDto;
import com.skolli.cms.payment_methods.dto.PaymentMethodItemDto;
import com.skolli.cms.payment_methods.dto.UpdatePaymentMethodDto;
import com.skolli.cms.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
@RequiredArgsConstructor
public class PaymentMethodsController {

    private final PaymentMethodsService paymentMethodsService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PaymentMethodItemDto>>> getAllPaymentMethods() {
        List<PaymentMethodItemDto> paymentMethods = this.paymentMethodsService.findAllPaymentMethods();
        return ResponseEntity.ok(ApiResponse.success("Payment methods retrieved successfully", paymentMethods));
    }

    @GetMapping("/{paymentMethodId}")
    public ResponseEntity<ApiResponse<PaymentMethodItemDto>> getPaymentMethodById(@PathVariable Long paymentMethodId) {
        PaymentMethodItemDto paymentMethod = this.paymentMethodsService.getPaymentMethodById(paymentMethodId);
        return ResponseEntity.ok(ApiResponse.success("Payment method retrieved successfully", paymentMethod));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<PaymentMethodItemDto>>> getPaymentMethodsByUserId(@PathVariable Long userId) {
        List<PaymentMethodItemDto> paymentMethods = this.paymentMethodsService.getPaymentMethodsByUserId(userId);
        return ResponseEntity.ok(ApiResponse.success("User payment methods retrieved successfully", paymentMethods));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentMethodItemDto>> createPaymentMethod(@RequestBody CreatePaymentMethodDto paymentMethodDto) {
        PaymentMethodItemDto createdPaymentMethod = this.paymentMethodsService.createPaymentMethod(paymentMethodDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Payment method created successfully", createdPaymentMethod));
    }

    @PutMapping("/{paymentMethodId}")
    public ResponseEntity<ApiResponse<Boolean>> updatePaymentMethod(
            @PathVariable Long paymentMethodId,
            @RequestBody UpdatePaymentMethodDto paymentMethodDto
    ) {
        Boolean updated = this.paymentMethodsService.updatePaymentMethod(paymentMethodId, paymentMethodDto);
        return ResponseEntity.ok(ApiResponse.success("Payment method updated successfully", updated));
    }

    @DeleteMapping("/{paymentMethodId}")
    public ResponseEntity<ApiResponse<Boolean>> deletePaymentMethod(@PathVariable Long paymentMethodId) {
        Boolean deleted = this.paymentMethodsService.deletePaymentMethod(paymentMethodId);
        return ResponseEntity.ok(ApiResponse.success("Payment method deleted successfully", deleted));
    }
}