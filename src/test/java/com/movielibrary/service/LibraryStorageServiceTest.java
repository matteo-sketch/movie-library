package com.movielibrary.service;

import com.movielibrary.model.Movie;
import com.movielibrary.model.MovieCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class LibraryStorageServiceTest {

    @TempDir
    Path tempDir; // JUnit crea una directory temporanea per noi

    @Test
    void saveAndLoadLibrary_ShouldPreserveData() throws StorageException {
        // Arrange
        Path filePath = tempDir.resolve("test-library.ser");
        LibraryStorageService storageService = new LibraryStorageService(filePath.toString());
        MovieCollection originalLibrary = new MovieCollection("Libreria di Test");
        originalLibrary.add(new Movie(1, "Inception", "A mind-bending thriller", "2010-07-16"));

        // Act
        storageService.saveLibrary(originalLibrary);
        MovieCollection loadedLibrary = (MovieCollection) storageService.loadLibrary();

        // Assert
        assertNotNull(loadedLibrary);
        assertEquals(originalLibrary.getName(), loadedLibrary.getName());
        assertEquals(1, loadedLibrary.getMovieCount());
        assertEquals("Inception", ((Movie) loadedLibrary.getComponents().get(0)).getTitle());
    }

    @Test
    void loadLibrary_WhenFileDoesNotExist_ShouldReturnNull() throws StorageException {
        // Arrange
        Path filePath = tempDir.resolve("non-existent-file.ser");
        LibraryStorageService storageService = new LibraryStorageService(filePath.toString());

        // Act
        MovieCollection loadedLibrary = (MovieCollection) storageService.loadLibrary();

        // Assert
        assertNull(loadedLibrary);
    }
}
