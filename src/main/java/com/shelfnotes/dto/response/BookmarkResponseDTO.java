package com.shelfnotes.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookmarkResponseDTO {

    private Long id;

    private Long studyMaterialId;

    private String studyMaterialTitle;

    private LocalDateTime createdAt;
}