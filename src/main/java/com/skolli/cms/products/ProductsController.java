package com.skolli.cms.products;

import com.skolli.cms.products.dto.CreatedProductDto;
import com.skolli.cms.products.dto.ProductItemDto;
import com.skolli.cms.products.dto.UpdatedProductDto;
import com.skolli.cms.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.ok(ApiResponse.success("Success", products));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductItemDto>> getProductById(@PathVariable  Long productId) {
        ProductItemDto product = this.productsService.getProductById(productId);
        return ResponseEntity.ok(ApiResponse.success("Success", product));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Products>> createProduct(@RequestBody CreatedProductDto products) {
        Products createdProduct = this.productsService.createProducts(products);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Product created", createdProduct));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse<Boolean>> updateProduct(
            @PathVariable Long productId,
            @RequestBody UpdatedProductDto product
    ) {
        Boolean updated = this.productsService.updateProduct(productId, product);
        return ResponseEntity.ok(ApiResponse.success("", updated));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<Boolean>> deleteProduct(@PathVariable Long productId) {
        Boolean deleted = this.productsService.deleteProduct(productId);
        return ResponseEntity.ok(ApiResponse.success("Deleted successfully", deleted));
    }
}
