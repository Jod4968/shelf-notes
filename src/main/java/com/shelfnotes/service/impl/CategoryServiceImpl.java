package com.shelfnotes.service.impl;

import com.shelfnotes.dto.request.CategoryRequestDTO;
import com.shelfnotes.dto.response.CategoryResponseDTO;
import com.shelfnotes.entity.Category;
import com.shelfnotes.entity.User;
import com.shelfnotes.exception.ResourceNotFoundException;
import com.shelfnotes.mapper.CategoryMapper;
import com.shelfnotes.repository.CategoryRepository;
import com.shelfnotes.repository.UserRepository;
import com.shelfnotes.service.CategoryService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryServiceImpl(
            CategoryRepository categoryRepository,
            UserRepository userRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    // Get currently logged-in user
    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );
    }

    // CREATE
    @Override
    public CategoryResponseDTO createCategory(
            CategoryRequestDTO requestDTO) {

        User user = getCurrentUser();

        // Check duplicate category for this user
        if (categoryRepository.existsByUserIdAndName(
                user.getId(),
                requestDTO.getName()
        )) {
            throw new RuntimeException(
                    "Category already exists"
            );
        }

        // DTO → Entity
        Category category =
                CategoryMapper.toEntity(requestDTO);

        // Attach logged-in user
        category.setUser(user);

        // Save
        Category savedCategory =
                categoryRepository.save(category);

        // Entity → Response DTO
        return CategoryMapper.toResponse(savedCategory);
    }

    // GET ALL CATEGORIES OF CURRENT USER
    @Override
    public List<CategoryResponseDTO> getMyCategories() {

        User user = getCurrentUser();

        return categoryRepository
                .findByUserId(user.getId())
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    // GET ONE CATEGORY
    @Override
    public CategoryResponseDTO getCategoryById(
            Long categoryId) {

        User user = getCurrentUser();

        Category category =
                categoryRepository
                        .findByIdAndUserId(
                                categoryId,
                                user.getId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category not found"
                                )
                        );

        return CategoryMapper.toResponse(category);
    }

    // UPDATE
    @Override
    public CategoryResponseDTO updateCategory(
            Long categoryId,
            CategoryRequestDTO requestDTO) {

        User user = getCurrentUser();

        Category category =
                categoryRepository
                        .findByIdAndUserId(
                                categoryId,
                                user.getId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category not found"
                                )
                        );

        // Check if another category of the same
        // user already has this name
        if (!category.getName().equals(requestDTO.getName())
                && categoryRepository.existsByUserIdAndName(
                user.getId(),
                requestDTO.getName()
        )) {

            throw new RuntimeException(
                    "Category already exists"
            );
        }

        category.setName(requestDTO.getName());

        Category updatedCategory =
                categoryRepository.save(category);

        return CategoryMapper.toResponse(updatedCategory);
    }

    // DELETE
    @Override
    public void deleteCategory(Long categoryId) {

        User user = getCurrentUser();

        Category category =
                categoryRepository
                        .findByIdAndUserId(
                                categoryId,
                                user.getId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Category not found"));


        categoryRepository.delete(category);
    }
}