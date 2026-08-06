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
    @Size(max = 150)
    private String title;

    @Size(max = 500)
    private String description;

    @NotNull(message = "Category is required")
    private Long categoryId;

    @NotNull(message = "Material type is required")
    private MaterialType materialType;

    @NotNull(message = "Visibility is required")
    private Visibility visibility;
}