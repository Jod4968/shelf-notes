package com.shelfnotes.repository;

import com.shelfnotes.entity.StudyMaterial;
import com.shelfnotes.enums.MaterialType;
import com.shelfnotes.enums.Visibility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyMaterialRepository
        extends JpaRepository<StudyMaterial, Long> {

    List<StudyMaterial> findByCategoryId(Long categoryId);

    List<StudyMaterial> findByTitleContainingIgnoreCase(String keyword);

    List<StudyMaterial> findByMaterialType(
            MaterialType materialType
    );

    List<StudyMaterial> findByVisibility(
            Visibility visibility
    );

    List<StudyMaterial> findByCategoryIdAndTitleContainingIgnoreCase(
            Long categoryId,
            String keyword
    );

    // USER-SPECIFIC SEARCH
    Page<StudyMaterial> findByCategoryUserIdAndTitleContainingIgnoreCase(
            Long userId,
            String keyword,
            Pageable pageable
    );

    Page<StudyMaterial> findByCategoryUserIdAndMaterialType(
            Long userId,
            MaterialType materialType,
            Pageable pageable
    );

    Page<StudyMaterial> findByCategoryUserIdAndVisibility(
            Long userId,
            Visibility visibility,
            Pageable pageable
    );

    Page<StudyMaterial> findByCategoryUserId(
            Long userId,
            Pageable pageable
    );}