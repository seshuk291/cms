package com.skolli.cms.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedProductDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Long stockInQuantity;
    private List<Long> Categories;
}
