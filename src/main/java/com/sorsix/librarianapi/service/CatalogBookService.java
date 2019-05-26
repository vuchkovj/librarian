package com.sorsix.librarianapi.service;

import com.sorsix.librarianapi.domain.CatalogBook;
import com.sorsix.librarianapi.repository.CatalogBookRepository;
import com.sorsix.librarianapi.api.error.BookNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogBookService {

    private final CatalogBookRepository repository;

    public CatalogBookService(CatalogBookRepository repository) {
        this.repository = repository;
    }

    public List<CatalogBook> getAllCatalogBooks() {
        return repository.findAll();
    }

    public CatalogBook getCatalogBookById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFound("Could not find book with id " + id));
    }

    public List<CatalogBook> getCatalogBooksByTitle(String title) {
        return repository.findAllByTitleContainingIgnoreCase(title);
    }

    public List<CatalogBook> getMostPopularBooks() {
        return repository.findMostPopular();
    }

    public List<CatalogBook> getSimilar(Long bookId) {
        return repository.findSimilar(bookId);
    }
}
