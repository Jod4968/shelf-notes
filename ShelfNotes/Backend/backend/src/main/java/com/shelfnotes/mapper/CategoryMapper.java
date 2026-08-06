package com.shelfnotes.mapper;

import com.shelfnotes.dto.request.CategoryRequestDTO;
import com.shelfnotes.dto.response.CategoryResponseDTO;
import com.shelfnotes.entity.Category;

public class CategoryMapper {

    private CategoryMapper() {
        // Prevent instantiation
    }

    /**
     * Converts CategoryRequestDTO to Category Entity
     */
    public static Category toEntity(CategoryRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        return Category.builder()
                .name(dto.getName())
                .build();
    }

    /**
     * Converts Category Entity to CategoryResponseDTO
     */
    public static CategoryResponseDTO toResponse(Category category) {

        if (category == null) {
            return null;
        }

        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .createdAt(category.getCreatedAt())
                .build();
    }
}