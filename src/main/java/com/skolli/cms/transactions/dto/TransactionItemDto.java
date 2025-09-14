package com.skolli.cms.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionItemDto {
    private Long id;
    private Boolean status;
    private BigDecimal totalAmount;
    private Long paymentMethodId;
    private String paymentType;
    private String paymentProcessor;
    private Long orderId;
}