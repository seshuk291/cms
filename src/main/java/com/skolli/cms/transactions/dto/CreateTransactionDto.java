package com.skolli.cms.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionDto {
    private Boolean status;
    private BigDecimal totalAmount;
    private Long paymentMethodId;
}