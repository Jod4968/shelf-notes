package com.shelfnotes.mapper;

import com.shelfnotes.dto.response.BookmarkResponseDTO;
import com.shelfnotes.entity.Bookmark;

public class BookmarkMapper {

    private BookmarkMapper() {
        // Prevent instantiation
    }

    /**
     * Converts Bookmark Entity to BookmarkResponseDTO
     */
    public static BookmarkResponseDTO toResponse(Bookmark bookmark) {

        if (bookmark == null) {
            return null;
        }

        return BookmarkResponseDTO.builder()
                .id(bookmark.getId())
                .studyMaterialId(bookmark.getStudyMaterial().getId())
                .studyMaterialTitle(bookmark.getStudyMaterial().getTitle())
                .createdAt(bookmark.getCreatedAt())
                .build();
    }
}