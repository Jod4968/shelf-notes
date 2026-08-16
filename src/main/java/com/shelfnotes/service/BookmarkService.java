package com.shelfnotes.service;

import com.shelfnotes.dto.response.BookmarkResponseDTO;

import java.util.List;

public interface BookmarkService {

    BookmarkResponseDTO addBookmark(Long studyMaterialId);

    List<BookmarkResponseDTO> getMyBookmarks();

    void removeBookmark(Long studyMaterialId);
}