package com.shelfnotes.service;

import com.shelfnotes.dto.request.CategoryRequestDTO;
import com.shelfnotes.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO);

    List<CategoryResponseDTO> getMyCategories();

    CategoryResponseDTO getCategoryById(Long categoryId);

    CategoryResponseDTO updateCategory(
            Long categoryId,
            CategoryRequestDTO requestDTO
    );

    void deleteCategory(Long categoryId);
}