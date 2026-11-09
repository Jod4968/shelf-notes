package com.shelfnotes.service.impl;

import com.shelfnotes.dto.response.BookmarkResponseDTO;
import com.shelfnotes.entity.Bookmark;
import com.shelfnotes.entity.StudyMaterial;
import com.shelfnotes.entity.User;
import com.shelfnotes.exception.ResourceAlreadyExistsException;
import com.shelfnotes.exception.ResourceNotFoundException;
import com.shelfnotes.mapper.BookmarkMapper;
import com.shelfnotes.repository.BookmarkRepository;
import com.shelfnotes.repository.StudyMaterialRepository;
import com.shelfnotes.repository.UserRepository;
import com.shelfnotes.service.BookmarkService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final StudyMaterialRepository studyMaterialRepository;
    private final UserRepository userRepository;

    public BookmarkServiceImpl(
            BookmarkRepository bookmarkRepository,
            StudyMaterialRepository studyMaterialRepository,
            UserRepository userRepository) {

        this.bookmarkRepository = bookmarkRepository;
        this.studyMaterialRepository = studyMaterialRepository;
        this.userRepository = userRepository;
    }

    // Get currently logged-in user
    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));
    }

    // ADD BOOKMARK
    @Override
    public BookmarkResponseDTO addBookmark(
            Long studyMaterialId) {

        User user = getCurrentUser();

        // Check whether material exists
        StudyMaterial studyMaterial =
                studyMaterialRepository
                        .findById(studyMaterialId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Study material not found"
                                ));

        // Prevent duplicate bookmark
        if (bookmarkRepository
                .existsByUserIdAndStudyMaterialId(
                        user.getId(),
                        studyMaterialId
                )) {

            throw new ResourceAlreadyExistsException(
                    "Study material is already bookmarked"
            );
        }

        Bookmark bookmark =
                Bookmark.builder()
                        .user(user)
                        .studyMaterial(studyMaterial)
                        .build();

        Bookmark savedBookmark =
                bookmarkRepository.save(bookmark);

        return BookmarkMapper.toResponse(savedBookmark);
    }

    // GET MY BOOKMARKS
    @Override
    public List<BookmarkResponseDTO> getMyBookmarks() {

        User user = getCurrentUser();

        return bookmarkRepository
                .findByUserId(user.getId())
                .stream()
                .map(BookmarkMapper::toResponse)
                .toList();
    }

    // REMOVE BOOKMARK
    @Override
    public void removeBookmark(
            Long studyMaterialId) {

        User user = getCurrentUser();

        Bookmark bookmark =
                bookmarkRepository
                        .findByUserIdAndStudyMaterialId(
                                user.getId(),
                                studyMaterialId
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Bookmark not found"
                                ));

        bookmarkRepository.delete(bookmark);
    }
}