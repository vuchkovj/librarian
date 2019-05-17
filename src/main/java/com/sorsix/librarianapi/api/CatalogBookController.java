package com.sorsix.librarianapi.api;

import com.sorsix.librarianapi.model.CatalogBook;
import com.sorsix.librarianapi.service.CatalogBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin({"*"})
@RestController
@RequestMapping("/catalog_books")
public class CatalogBookController {
    private final CatalogBookService service;

    public CatalogBookController(CatalogBookService service) {
        this.service = service;
    }

    @GetMapping
    public List<CatalogBook> getAllCatalogBooks() {
        return service.getAllCatalogBooks();
    }

    @GetMapping("/available")
    public List<CatalogBook> getAllAvailable() {
        return service.getAllAvailable();
    }

    @GetMapping("/author/{author}")
    public List<CatalogBook> getCatalogBooksByAuthor(@PathVariable String author) {
        return service.getCatalogBooksByAuthor(author);
    }

    @GetMapping("/genre/{genre}")
    public List<CatalogBook> getCatalogBooksByGenre(@PathVariable String genre) {
        return service.getCatalogBooksByGenre(genre);
    }
}
