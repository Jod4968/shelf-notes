package com.shelfnotes.service.impl;

import com.shelfnotes.dto.request.StudyMaterialRequestDTO;
import com.shelfnotes.dto.response.StudyMaterialResponseDTO;
import com.shelfnotes.entity.Category;
import com.shelfnotes.entity.StudyMaterial;
import com.shelfnotes.entity.User;
import com.shelfnotes.enums.MaterialType;
import com.shelfnotes.enums.Visibility;
import com.shelfnotes.exception.ResourceNotFoundException;
import com.shelfnotes.mapper.StudyMaterialMapper;
import com.shelfnotes.repository.CategoryRepository;
import com.shelfnotes.repository.StudyMaterialRepository;
import com.shelfnotes.repository.UserRepository;
import com.shelfnotes.service.StudyMaterialService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudyMaterialServiceImpl implements StudyMaterialService {

    private final StudyMaterialRepository studyMaterialRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public StudyMaterialServiceImpl(
            StudyMaterialRepository studyMaterialRepository,
            CategoryRepository categoryRepository,
            UserRepository userRepository) {

        this.studyMaterialRepository = studyMaterialRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    // Get currently logged-in user
    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));
    }

    // CREATE
    @Override
    public StudyMaterialResponseDTO createMaterial(
            StudyMaterialRequestDTO requestDTO) {

        User user = getCurrentUser();

        Category category =
                categoryRepository
                        .findByIdAndUserId(
                                requestDTO.getCategoryId(),
                                user.getId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Category not found"
                                ));

        StudyMaterial material =
                StudyMaterialMapper.toEntity(requestDTO);

        material.setCategory(category);

        StudyMaterial savedMaterial =
                studyMaterialRepository.save(material);

        return StudyMaterialMapper.toResponse(savedMaterial);
    }

    // GET MY MATERIALS - PAGINATION + SORTING
    @Override
    public Page<StudyMaterialResponseDTO> getMyMaterials(
            Pageable pageable) {

        User user = getCurrentUser();

        Page<StudyMaterial> materials =
                studyMaterialRepository
                        .findByCategoryUserId(
                                user.getId(),
                                pageable
                        );

        return materials.map(
                StudyMaterialMapper::toResponse
        );
    }

    // GET ONE MATERIAL
    @Override
    public StudyMaterialResponseDTO getMaterialById(
            Long materialId) {

        User user = getCurrentUser();

        StudyMaterial material =
                studyMaterialRepository
                        .findById(materialId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Study material not found"
                                ));

        // Ownership check
        if (!material.getCategory()
                .getUser()
                .getId()
                .equals(user.getId())) {

            throw new ResourceNotFoundException(
                    "Study material not found"
            );
        }

        return StudyMaterialMapper.toResponse(material);
    }

    // UPDATE
    @Override
    public StudyMaterialResponseDTO updateMaterial(
            Long materialId,
            StudyMaterialRequestDTO requestDTO) {

        User user = getCurrentUser();

        StudyMaterial material =
                studyMaterialRepository
                        .findById(materialId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Study material not found"
                                ));

        // Ownership check
        if (!material.getCategory()
                .getUser()
                .getId()
                .equals(user.getId())) {

            throw new ResourceNotFoundException(
                    "Study material not found"
            );
        }

        // Check new category belongs to current user
        Category category =
                categoryRepository
                        .findByIdAndUserId(
                                requestDTO.getCategoryId(),
                                user.getId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Category not found"
                                ));

        // Update fields
        material.setTitle(requestDTO.getTitle());
        material.setDescription(requestDTO.getDescription());
        material.setMaterialType(requestDTO.getMaterialType());
        material.setVisibility(requestDTO.getVisibility());
        material.setResourceLocation(
                requestDTO.getResourceLocation()
        );
        material.setFileSize(requestDTO.getFileSize());
        material.setCategory(category);

        StudyMaterial updatedMaterial =
                studyMaterialRepository.save(material);

        return StudyMaterialMapper.toResponse(updatedMaterial);
    }

    // DELETE
    @Override
    public void deleteMaterial(Long materialId) {

        User user = getCurrentUser();

        StudyMaterial material =
                studyMaterialRepository
                        .findById(materialId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Study material not found"
                                ));

        // Ownership check
        if (!material.getCategory()
                .getUser()
                .getId()
                .equals(user.getId())) {

            throw new ResourceNotFoundException(
                    "Study material not found"
            );
        }

        studyMaterialRepository.delete(material);
    }

    // SEARCH MATERIALS BY TITLE

    @Override
    public Page<StudyMaterialResponseDTO> searchMaterials(
            String keyword,
            Pageable pageable) {

        User user = getCurrentUser();

        Page<StudyMaterial> materials =
                studyMaterialRepository
                        .findByCategoryUserIdAndTitleContainingIgnoreCase(
                                user.getId(),
                                keyword,
                                pageable
                        );

        return materials.map(
                StudyMaterialMapper::toResponse
        );
    }

    // FILTER BY MATERIAL TYPE
    @Override
    public Page<StudyMaterialResponseDTO> getMaterialsByType(
            MaterialType materialType,
            Pageable pageable) {

        User user = getCurrentUser();

        Page<StudyMaterial> materials =
                studyMaterialRepository
                        .findByCategoryUserIdAndMaterialType(
                                user.getId(),
                                materialType,
                                pageable
                        );

        return materials.map(
                StudyMaterialMapper::toResponse
        );
    }

    // FILTER BY VISIBILITY
    @Override
    public Page<StudyMaterialResponseDTO> getMaterialsByVisibility(
            Visibility visibility,
            Pageable pageable) {

        User user = getCurrentUser();

        Page<StudyMaterial> materials =
                studyMaterialRepository
                        .findByCategoryUserIdAndVisibility(
                                user.getId(),
                                visibility,
                                pageable
                        );

        return materials.map(
                StudyMaterialMapper::toResponse
        );
    }
}