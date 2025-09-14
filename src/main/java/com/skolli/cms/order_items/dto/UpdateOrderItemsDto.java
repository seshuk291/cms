package com.skolli.cms.order_items.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderItemsDto {
    private Integer quantity;
    private Double unitPrice;
}