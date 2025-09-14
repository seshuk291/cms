package com.skolli.cms.payment_methods.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentMethodDto {
    private String paymentType;
    private String paymentProcessor;
}