
    package com.shelfnotes.dto.response;

import com.shelfnotes.enums.MaterialType;
import com.shelfnotes.enums.Visibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class StudyMaterialResponseDTO {

        private Long id;

        private String title;

        private String description;

        private MaterialType materialType;

        private Visibility visibility;

        private String resourceLocation;

        private Long fileSize;

        private CategoryResponseDTO category;

        private LocalDateTime createdAt;
    }
