package com.shelfnotes.controller;

import com.shelfnotes.dto.request.CategoryRequestDTO;
import com.shelfnotes.dto.response.CategoryResponseDTO;
import com.shelfnotes.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(
            @Valid @RequestBody CategoryRequestDTO requestDTO) {

        CategoryResponseDTO response =
                categoryService.createCategory(requestDTO);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    // GET ALL MY CATEGORIES
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getMyCategories() {

        List<CategoryResponseDTO> categories =
                categoryService.getMyCategories();

        return ResponseEntity.ok(categories);
    }

    // GET ONE CATEGORY
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(
            @PathVariable Long id) {

        CategoryResponseDTO response =
                categoryService.getCategoryById(id);

        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequestDTO requestDTO) {

        CategoryResponseDTO response =
                categoryService.updateCategory(id, requestDTO);

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }
}