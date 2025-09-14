package com.skolli.cms.order_items.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemsItemDto {
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private Long productId;
    private String productName;
    private Long orderId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}