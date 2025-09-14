package com.skolli.cms.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDto {
    private String status;
    private BigDecimal totalAmount;
    private Long billingAddressId;
    private Long shippingAddressId;
}