package com.skolli.cms.categories;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skolli.cms.categories.dto.CategoriesDto;
import com.skolli.cms.categories.dto.CreateCategoryDto;
import com.skolli.cms.categories.dto.UpdateCategoryDto;
import com.skolli.cms.utils.ApiResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoriesDto>>> getAllCategories() {
        var categories = categoriesService.findAllCategories();
        return ResponseEntity.ok(ApiResponse.success("Success", categories));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriesDto>> getCategoryById(@PathVariable Long id) {
        CategoriesDto category = categoriesService.getCategoryById(id);
        return ResponseEntity.ok(ApiResponse.success("Success", category));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoriesDto>> createCategory(@RequestBody CreateCategoryDto categoryDto) {
        CategoriesDto createdCategory = categoriesService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Success", createdCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> updateCategory(
            @PathVariable Long id,
            @RequestBody UpdateCategoryDto categoryDto
    ) {
        Boolean updated = categoriesService.updateCategory(id, categoryDto);
        return ResponseEntity.ok(ApiResponse.success("Category updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteCategory(@PathVariable Long id) {
        Boolean deleted = categoriesService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success("Category deleted successfully", deleted));
    }
}
