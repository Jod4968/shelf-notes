package com.shelfnotes.entity;

import com.shelfnotes.enums.MaterialType;
import com.shelfnotes.enums.Visibility;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "study_materials",
        indexes = {
                @Index(
                        name = "idx_material_category",
                        columnList = "category_id"
                ),
                @Index(
                        name = "idx_material_type",
                        columnList = "material_type"
                ),
                @Index(
                        name = "idx_material_visibility",
                        columnList = "visibility"
                ),
                @Index(
                        name = "idx_material_created_at",
                        columnList = "created_at"
                ),
                @Index(
                        name = "idx_material_category_created",
                        columnList = "category_id, created_at"
                )
        }
)
public class StudyMaterial extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false, length = 150)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Visibility visibility;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MaterialType materialType;

    @NotBlank(message = "Resource location is required")
    @Column(nullable = false, length = 500)
    private String resourceLocation;

    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}