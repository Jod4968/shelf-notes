package com.shelfnotes.controller;

import com.shelfnotes.dto.request.StudyMaterialRequestDTO;
import com.shelfnotes.dto.response.StudyMaterialResponseDTO;
import com.shelfnotes.enums.MaterialType;
import com.shelfnotes.enums.Visibility;
import com.shelfnotes.service.StudyMaterialService;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/materials")
public class StudyMaterialController {

    private final StudyMaterialService studyMaterialService;

    public StudyMaterialController(
            StudyMaterialService studyMaterialService) {
        this.studyMaterialService = studyMaterialService;
    }

    // CREATE
    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<StudyMaterialResponseDTO> createMaterial(
            @Valid @ModelAttribute StudyMaterialRequestDTO requestDTO)
            throws IOException {

        StudyMaterialResponseDTO response =
                studyMaterialService.createMaterial(requestDTO);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    // GET MY MATERIALS - PAGINATION + SORTING
    @GetMapping
    public ResponseEntity<Page<StudyMaterialResponseDTO>>
    getMyMaterials(Pageable pageable) {

        return ResponseEntity.ok(
                studyMaterialService.getMyMaterials(pageable)
        );
    }

    // SEARCH BY TITLE
    @GetMapping("/search")
    public ResponseEntity<Page<StudyMaterialResponseDTO>>
    searchMaterials(
            @RequestParam String keyword,
            Pageable pageable) {

        return ResponseEntity.ok(
                studyMaterialService.searchMaterials(
                        keyword,
                        pageable
                )
        );
    }

    // FILTER BY MATERIAL TYPE
    @GetMapping("/type/{materialType}")
    public ResponseEntity<Page<StudyMaterialResponseDTO>>
    getMaterialsByType(
            @PathVariable MaterialType materialType,
            Pageable pageable) {

        return ResponseEntity.ok(
                studyMaterialService.getMaterialsByType(
                        materialType,
                        pageable
                )
        );
    }

    // FILTER BY VISIBILITY
    @GetMapping("/visibility/{visibility}")
    public ResponseEntity<Page<StudyMaterialResponseDTO>>
    getMaterialsByVisibility(
            @PathVariable Visibility visibility,
            Pageable pageable) {

        return ResponseEntity.ok(
                studyMaterialService.getMaterialsByVisibility(
                        visibility,
                        pageable
                )
        );
    }


    // GET ONE MATERIAL
    @GetMapping("/{id}")
    public ResponseEntity<StudyMaterialResponseDTO>
    getMaterialById(@PathVariable Long id) {

        return ResponseEntity.ok(
                studyMaterialService.getMaterialById(id)
        );
    }

    // DOWNLOAD FILE
    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadMaterial(
            @PathVariable Long id) throws IOException {

        Resource resource =
                studyMaterialService.downloadMaterial(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                resource.getFilename() +
                                "\""
                )
                .body(resource);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<StudyMaterialResponseDTO>
    updateMaterial(
            @PathVariable Long id,
            @Valid @RequestBody StudyMaterialRequestDTO requestDTO) {

        return ResponseEntity.ok(
                studyMaterialService.updateMaterial(
                        id,
                        requestDTO
                )
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteMaterial(@PathVariable Long id) {

        studyMaterialService.deleteMaterial(id);

        return ResponseEntity.noContent().build();
    }
}