package com.shelfnotes.mapper;

import com.shelfnotes.dto.request.StudyMaterialRequestDTO;
import com.shelfnotes.dto.response.StudyMaterialResponseDTO;
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

        return response;
    }
}