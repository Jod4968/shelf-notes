package com.shelfnotes.repository;

import com.shelfnotes.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository
        extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUserId(Long userId);

    Optional<Bookmark> findByUserIdAndStudyMaterialId(
            Long userId,
            Long studyMaterialId
    );

    boolean existsByUserIdAndStudyMaterialId(
            Long userId,
            Long studyMaterialId
    );

    void deleteByUserIdAndStudyMaterialId(
            Long userId,
            Long studyMaterialId
    );
}