package com.skolli.cms.products;

import com.skolli.cms.products.dto.CreatedProductDto;
import com.skolli.cms.products.dto.ProductItemDto;
import com.skolli.cms.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductItemDto>>> getAllProducts() {
        List<ProductItemDto> products = this.productsService.findAllProducts();
        return ResponseEntity.ok().body(ApiResponse.success("Success", products));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Products>> createProduct(@RequestBody CreatedProductDto products) {
        Products createdProduct = this.productsService.createProducts(products);
        return ResponseEntity.ok().body(ApiResponse.success("Product created", createdProduct));
    }
}
