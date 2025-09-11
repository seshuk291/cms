package com.skolli.cms.products;

import com.skolli.cms.categories.Categories;
import com.skolli.cms.categories.CategoriesRepository;
import com.skolli.cms.common.custom_exceptions.CategoriesNotFoundException;
import com.skolli.cms.common.custom_exceptions.ProductCreationException;
import com.skolli.cms.products.dto.CreatedProductDto;
import com.skolli.cms.products.dto.ProductItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final CategoriesRepository categoriesRepository;

    public List<ProductItemDto> findAllProducts() {
        return this.productsRepository.findAll()
                .stream()
                .map((product) -> {
                    return ProductItemDto
                            .builder()
                            .id(product.getId())
                            .name(product.getName())
                            .description(product.getDescription())
                            .price(product.getPrice())
                            .stockInQuantity(product.getStockQuantity())
                            .categories(product.getCategories())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public Products createProducts(CreatedProductDto productDto) {
        List<Categories> categories = categoriesRepository.findByCategoriesIds(productDto.getCategories())
                .get();
        if(categories.isEmpty()) {
            throw new CategoriesNotFoundException("Categories not found");
        }
        try {
            Products products = Products.builder()
                    .name(productDto.getName())
                    .description(productDto.getDescription())
                    .price(productDto.getPrice())
                    .stockQuantity(productDto.getStockInQuantity())
                    .categories(categories)
                    .build();

            return this.productsRepository.save(products);
        } catch(Exception exception) {
            throw new ProductCreationException("Unable to create product." + exception.getMessage());
        }
    }
}
