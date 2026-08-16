package com.shelfnotes.mapper;

import com.shelfnotes.dto.request.StudyMaterialRequestDTO;
import com.shelfnotes.dto.response.StudyMaterialResponseDTO;
import com.shelfnotes.entity.StudyMaterial;

public class StudyMaterialMapper {

    private StudyMaterialMapper() {
        // Prevent object creation
    }

    // Request DTO → Entity
    public static StudyMaterial toEntity(
            StudyMaterialRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        return StudyMaterial.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .materialType(dto.getMaterialType())
                .visibility(dto.getVisibility())
                .resourceLocation(dto.getResourceLocation())
                .fileSize(dto.getFileSize())
                .build();
    }

    // Entity → Response DTO
    public static StudyMaterialResponseDTO toResponse(
            StudyMaterial material) {

        if (material == null) {
            return null;
        }

        return StudyMaterialResponseDTO.builder()
                .id(material.getId())
                .title(material.getTitle())
                .description(material.getDescription())
                .materialType(material.getMaterialType())
                .visibility(material.getVisibility())
                .resourceLocation(material.getResourceLocation())
                .fileSize(material.getFileSize())
                .category(
                        CategoryMapper.toResponse(
                                material.getCategory()
                        )
                )
                .createdAt(material.getCreatedAt())
                .build();
    }
}