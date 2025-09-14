package com.skolli.cms.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {
    private Long id;
    private Long userId;
    private String userName;
    private Timestamp orderDate;
    private String status;
    private BigDecimal totalAmount;
    private Long billingAddressId;
    private Long shippingAddressId;
    private Long transactionId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}