package com.skolli.cms.products;

import com.skolli.cms.categories.Categories;
import com.skolli.cms.categories.CategoriesRepository;
import com.skolli.cms.common.custom_exceptions.CategoriesNotFoundException;
import com.skolli.cms.common.custom_exceptions.ProductCreationException;
import com.skolli.cms.common.custom_exceptions.ProductNotFoundException;
import com.skolli.cms.common.custom_exceptions.ProductUpdateException;
import com.skolli.cms.products.dto.CreatedProductDto;
import com.skolli.cms.products.dto.ProductItemDto;
import com.skolli.cms.products.dto.UpdatedProductDto;
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
                .map(ProductsService::mapProductToDto)
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

    public Boolean updateProduct(Long productId, UpdatedProductDto productDto) {
        Products product = this.productsRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not found"));
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        product.setStockQuantity(productDto.quantity());
        try {
            this.productsRepository.save(product);
            return true;
        } catch(Exception exception) {
            throw new ProductUpdateException("Unable to update the product " + exception.getMessage());
        }
    }

    public Boolean deleteProduct(Long productId) {
        try {
            this.productsRepository.deleteById(productId);
            return true;
        } catch(Exception exception) {
            throw new RuntimeException("Unable to delete product" + exception.getMessage());
        }
    }

    public ProductItemDto getProductById(Long productId) {
        Products product =  this.productsRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return mapProductToDto(product);
    }

    private static ProductItemDto mapProductToDto(Products product) {
        return ProductItemDto
                .builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockInQuantity(product.getStockQuantity())
                .categories(product.getCategories())
                .build();
    }
}
