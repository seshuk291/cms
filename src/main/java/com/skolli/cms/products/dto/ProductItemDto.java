package com.skolli.cms.products.dto;

import com.skolli.cms.categories.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductItemDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long stockInQuantity;
    private List<Categories> categories;
}
