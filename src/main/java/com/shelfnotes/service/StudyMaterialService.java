package com.shelfnotes.service;

import com.shelfnotes.dto.request.StudyMaterialRequestDTO;
import com.shelfnotes.dto.response.StudyMaterialResponseDTO;
import com.shelfnotes.enums.MaterialType;
import com.shelfnotes.enums.Visibility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface StudyMaterialService {

    // CREATE
    StudyMaterialResponseDTO createMaterial(
            StudyMaterialRequestDTO requestDTO
    ) throws IOException;

    // GET MY MATERIALS - PAGINATION + SORTING
    Page<StudyMaterialResponseDTO> getMyMaterials(
            Pageable pageable
    );

    // GET ONE
    StudyMaterialResponseDTO getMaterialById(
            Long materialId
    );

    // UPDATE
    StudyMaterialResponseDTO updateMaterial(
            Long materialId,
            StudyMaterialRequestDTO requestDTO
    );

    // DELETE
    void deleteMaterial(
            Long materialId
    );

    // SEARCH
    Page<StudyMaterialResponseDTO> searchMaterials(
            String keyword,
            Pageable pageable
    );

    // FILTER BY TYPE
    Page<StudyMaterialResponseDTO> getMaterialsByType(
            MaterialType materialType,
            Pageable pageable
    );

    // FILTER BY VISIBILITY
    Page<StudyMaterialResponseDTO> getMaterialsByVisibility(
            Visibility visibility,
            Pageable pageable
    );
}