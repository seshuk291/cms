package com.skolli.cms.products.dto;

import java.math.BigDecimal;

public record UpdatedProductDto(Long id, String name, String description, BigDecimal price, Long quantity) {
}
