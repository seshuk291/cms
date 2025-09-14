package com.skolli.cms.order_items.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderItemsDto {
    private Integer quantity;
    private Double unitPrice;
    private Long productId;
    private Long orderId;
}