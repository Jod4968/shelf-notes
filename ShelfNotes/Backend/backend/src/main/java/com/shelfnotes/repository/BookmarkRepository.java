package com.shelfnotes.repository;

import com.shelfnotes.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUserId(Long userId);

    boolean existsByUserIdAndStudyMaterialId(Long userId, Long studyMaterialId);

    void deleteByUserIdAndStudyMaterialId(Long userId, Long studyMaterialId);
}