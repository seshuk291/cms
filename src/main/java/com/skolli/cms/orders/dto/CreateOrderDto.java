package com.skolli.cms.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDto {
    private Long userId;
    private Timestamp orderDate;
    private String status;
    private BigDecimal totalAmount;
    private Long billingAddressId;
    private Long shippingAddressId;
    private Long transactionId;
}