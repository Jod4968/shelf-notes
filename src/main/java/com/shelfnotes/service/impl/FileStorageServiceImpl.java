package com.shelfnotes.service.impl;

import com.shelfnotes.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path uploadPath;

    public FileStorageServiceImpl(
            @Value("${file.upload-dir}") String uploadDir) {
        this.uploadPath = Paths.get(uploadDir)
                .toAbsolutePath()
                .normalize();
        try {
            Files.createDirectories(uploadPath);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not create upload directory",
                    e
            );
        }
    }
    @Override
    public String storeFile(MultipartFile file)
            throws IOException {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException(
                    "File cannot be empty"
            );
        }

        String originalFilename =
                file.getOriginalFilename();

        String extension = "";

        if (originalFilename != null
                && originalFilename.contains(".")) {

            extension =
                    originalFilename.substring(
                            originalFilename.lastIndexOf(".")
                    );
        }

        String filename =
                UUID.randomUUID() + extension;

        Path targetLocation =
                uploadPath.resolve(filename)
                        .normalize();

        Files.copy(
                file.getInputStream(),
                targetLocation,
                StandardCopyOption.REPLACE_EXISTING
        );

        return targetLocation.toString();
    }
    @Override
    public Resource loadFile(String resourceLocation)
            throws IOException {

        Path filePath = Paths.get(resourceLocation)
                .toAbsolutePath()
                .normalize();

        if (!Files.exists(filePath)) {
            throw new IOException("File not found: " + resourceLocation);
        }

        if (!Files.isReadable(filePath)) {
            throw new IOException("File is not readable: " + resourceLocation);
        }

        return new FileSystemResource(filePath);
    }
}