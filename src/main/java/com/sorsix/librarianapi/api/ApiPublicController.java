package com.sorsix.librarianapi.api;

import com.sorsix.librarianapi.domain.CatalogBook;
import com.sorsix.librarianapi.service.CatalogBookService;
import com.sorsix.librarianapi.api.error.BookNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Public API Rest Controller
 *
 * Anonymous, User and Admin roles can access these api endpoints
 */
@RestController
@RequestMapping("/api/public")
public class ApiPublicController {
    private final Logger logger = LoggerFactory.getLogger(ApiPublicController.class);
    private final CatalogBookService service;

    public ApiPublicController(CatalogBookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    public List<CatalogBook> getAllCatalogBooks() {
        return service.getAllCatalogBooks();
    }

    @GetMapping("/books/{id}")
    public CatalogBook getCatalogBook(@PathVariable Long id) {
        return service.getCatalogBookById(id);
    }

    @GetMapping("/books/popular")
    public List<CatalogBook> getMostPopular() {
        return service.getMostPopularBooks();
    }

    @GetMapping("/books/similar")
    public List<CatalogBook> getSimilar(@RequestParam("bookId") Long bookId) {
        return service.getSimilar(bookId);
    }

    @GetMapping("/books/search")
    public List<CatalogBook> getCatalogBookByTitle(@RequestParam("title") String title) {
        return service.getCatalogBooksByTitle(title);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void onGetCatalogBookError(BookNotFound e) {
        logger.warn("BookNotFoundError [{}]", e.getMessage());
    }
}
