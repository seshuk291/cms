package com.skolli.cms.payment_methods.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethodItemDto {
    private Long id;
    private String paymentType;
    private String paymentProcessor;
    private Long userId;
    private String userName;
}