package com.sorsix.librarianapi.api;

import com.sorsix.librarianapi.model.CatalogBook;
import com.sorsix.librarianapi.service.CatalogBookService;
import com.sorsix.librarianapi.service.exceptions.BookNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin({"*"})
@RestController
@RequestMapping("/catalog_books")
public class CatalogBookController {
    private final Logger logger = LoggerFactory.getLogger(CatalogBookController.class);

    private final CatalogBookService service;

    public CatalogBookController(CatalogBookService service) {
        this.service = service;
    }

    @GetMapping
    public List<CatalogBook> getAllCatalogBooks() {
        return service.getAllCatalogBooks();
    }

    @GetMapping("/{id}")
    public CatalogBook getCatalogBook(@PathVariable Long id) {
        return service.getCatalogBookById(id);
    }

    @GetMapping("/search/book")
    public List<CatalogBook> getCatalogBookByTitle(@RequestParam String title) {
        return service.getCatalogBooksByTitle(title);
    }

    @GetMapping("/popular")
    List<CatalogBook> getMostPopular() {
        return service.getMostPopularBooks();
    }

    @GetMapping("/similar/{bookId}")
    List<CatalogBook> getSimilar(@PathVariable("bookId") Long bookId) {
        return service.getSimilar(bookId);
    }

//    @GetMapping("/author/{author}")
//    public List<CatalogBook> getCatalogBooksByAuthor(@PathVariable String author) {
//        return service.getCatalogBooksByAuthor(author);
//    }
//
//    @GetMapping("/genre/{genre}")
//    public List<CatalogBook> getCatalogBooksByGenre(@PathVariable String genre) {
//        return service.getCatalogBooksByGenre(genre);
//    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void onBookNotFoundError(BookNotFound e) {
        logger.warn("onBookNotFoundError [{}]", e.toString());
    }
}
