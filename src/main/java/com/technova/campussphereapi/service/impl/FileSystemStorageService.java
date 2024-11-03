package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.service.StorageService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileSystemStorageService implements StorageService {

    @Value("${storage.location}")
    private String storageLocation;

    private Path rootLocation;

    @PostConstruct
    @Override
    public void inii() {
        if (storageLocation.trim().isEmpty()) {
            throw new RuntimeException("File upload location can not be Empty");
        }

        rootLocation = Paths.get(storageLocation);

        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    @Override
    public String store(MultipartFile file) {
        try {
            if(file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }
            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(originalFilename);

            Path destinationFile = this.rootLocation.resolve(Paths.get(fileName)).normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                //This is a security check
                throw new RuntimeException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream,destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new ResourceNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new ResourceNotFoundException("Could not read file: " + filename);
        }
    }

    @Override
    public void delete(String filename) {
        Path file = load(filename);
        try {
            FileSystemUtils.deleteRecursively(file);
        } catch (IOException e) {
            throw new RuntimeException("Cannot delete the file");
        }
    }
}
