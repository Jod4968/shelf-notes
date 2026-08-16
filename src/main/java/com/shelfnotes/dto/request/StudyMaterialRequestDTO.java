package com.shelfnotes.dto.request;

import com.shelfnotes.enums.MaterialType;
import com.shelfnotes.enums.Visibility;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyMaterialRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Title cannot exceed 150 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotNull(message = "Material type is required")
    private MaterialType materialType;

    @NotNull(message = "Visibility is required")
    private Visibility visibility;

    @NotBlank(message = "Resource location is required")
    private String resourceLocation;

    private Long fileSize;

    @NotNull(message = "Category ID is required")
    private Long categoryId;
}