package com.shelfnotes.controller;

import com.shelfnotes.dto.response.BookmarkResponseDTO;
import com.shelfnotes.service.BookmarkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    // ADD BOOKMARK
    @PostMapping("/{studyMaterialId}")
    public ResponseEntity<BookmarkResponseDTO> addBookmark(
            @PathVariable Long studyMaterialId) {

        BookmarkResponseDTO response =
                bookmarkService.addBookmark(studyMaterialId);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    // GET MY BOOKMARKS
    @GetMapping
    public ResponseEntity<List<BookmarkResponseDTO>> getMyBookmarks() {

        List<BookmarkResponseDTO> bookmarks =
                bookmarkService.getMyBookmarks();

        return ResponseEntity.ok(bookmarks);
    }

    // REMOVE BOOKMARK
    @DeleteMapping("/{studyMaterialId}")
    public ResponseEntity<Void> removeBookmark(
            @PathVariable Long studyMaterialId) {

        bookmarkService.removeBookmark(studyMaterialId);

        return ResponseEntity.noContent().build();
    }
}