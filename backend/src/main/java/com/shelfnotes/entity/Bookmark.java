package com.shelfnotes.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "bookmarks",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "user_id",
                        "study_material_id"
                })
        }
)
public class Bookmark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_material_id", nullable = false)
    private StudyMaterial studyMaterial;
}
