package com.shelfnotes.mapper;

import com.shelfnotes.dto.request.StudyMaterialRequestDTO;
import com.shelfnotes.dto.response.StudyMaterialResponseDTO;
import com.shelfnotes.dto.response.CategoryResponseDTO;
import com.shelfnotes.entity.StudyMaterial;

public class StudyMaterialMapper {

    // DTO → Entity
    public static StudyMaterial toEntity(
            StudyMaterialRequestDTO dto) {

        return StudyMaterial.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .materialType(dto.getMaterialType())
                .visibility(dto.getVisibility())
                .build();
    }

    // Entity → Response DTO
    public static StudyMaterialResponseDTO toResponse(
            StudyMaterial material) {

        StudyMaterialResponseDTO response =
                new StudyMaterialResponseDTO();

        response.setId(material.getId());
        response.setTitle(material.getTitle());
        response.setDescription(material.getDescription());
        response.setMaterialType(material.getMaterialType());
        response.setVisibility(material.getVisibility());

        response.setResourceLocation(
                material.getResourceLocation()
        );

        response.setFileSize(
                material.getFileSize()
        );

        // Category
        if (material.getCategory() != null) {

            CategoryResponseDTO category =
                    new CategoryResponseDTO();

            category.setId(
                    material.getCategory().getId()
            );

            category.setName(
                    material.getCategory().getName()
            );

            response.setCategory(category);
        }

        // Created date
        response.setCreatedAt(
                material.getCreatedAt()
        );

        return response;
    }
}