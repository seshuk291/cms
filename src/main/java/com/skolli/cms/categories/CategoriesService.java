package com.skolli.cms.categories;

import java.util.List;

import org.springframework.stereotype.Service;
import com.skolli.cms.categories.dto.CategoriesDto;
import com.skolli.cms.categories.dto.CreateCategoryDto;
import com.skolli.cms.categories.dto.UpdateCategoryDto;
import com.skolli.cms.common.custom_exceptions.CategoriesNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    public List<CategoriesDto> findAllCategories() {
        return this.categoriesRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public CategoriesDto getCategoryById(Long categoryId) {
        Categories category = this.categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new CategoriesNotFoundException("Category not found"));
        return mapToDto(category);
    }

    public CategoriesDto createCategory(CreateCategoryDto categoryDto) {

        categoriesRepository.findCategoriesByName(categoryDto.name()).ifPresent((category) -> {
                throw new RuntimeException("Category already exist");
        });

        Categories categories = Categories.builder()
                .name(categoryDto.name())
                .description(categoryDto.description())
                .build();
        this.categoriesRepository.save(categories);
        return mapToDto(categories);
    }

    public Boolean updateCategory(Long categoryId, UpdateCategoryDto categoryDto) {
        Categories category = this.categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new CategoriesNotFoundException("Category not found"));
        category.setName(categoryDto.name());
        category.setDescription(categoryDto.description());
        this.categoriesRepository.save(category);
        return true;
    }

    public Boolean deleteCategory(Long categoryId) {
        Categories category = this.categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new CategoriesNotFoundException("Category not found"));
        this.categoriesRepository.delete(category);
        return true;
    }

    public CategoriesDto mapToDto(Categories category) {
        return new CategoriesDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
