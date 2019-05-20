package com.sorsix.librarianapi.service;

import com.sorsix.librarianapi.model.CatalogBook;
import com.sorsix.librarianapi.repository.CatalogBookRepository;
import com.sorsix.librarianapi.service.exceptions.BookNotFound;
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
        return repository.findById(id).orElseThrow(BookNotFound::new);
    }

    public List<CatalogBook> getCatalogBooksByAuthor(String author) {
        return repository.findAllByAuthor_Name(author);
    }

    public List<CatalogBook> getCatalogBooksByGenre(String genre) {
        return repository.findAllByGenre_Name(genre);
    }

    //TODO Use 'contains'
    public List<CatalogBook> getCatalogBookByTitle(String title) {
        return repository.findAllByTitle(title);
    }

//    public List<CatalogBook> getAllAvailable() {
//        return repository.findAllAvailable();
//    }
}
