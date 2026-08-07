package com.shelfnotes.repository;

import com.shelfnotes.entity.Category;
import com.shelfnotes.entity.StudyMaterial;
import com.shelfnotes.enums.MaterialType;
import com.shelfnotes.enums.Visibility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUserId(Long userId);
    boolean existsByUserIdAndName(Long userId, String name);

    interface StudyMaterialRepository extends JpaRepository<StudyMaterial, Long> {

        List<StudyMaterial> findByCategoryId(Long categoryId);

        List<StudyMaterial> findByTitleContainingIgnoreCase(String keyword);

        List<StudyMaterial> findByMaterialType(MaterialType materialType);

        List<StudyMaterial> findByVisibility(Visibility visibility);

        List<StudyMaterial> findByCategoryIdAndTitleContainingIgnoreCase(
                Long categoryId,
                String keyword
        );
    }
}