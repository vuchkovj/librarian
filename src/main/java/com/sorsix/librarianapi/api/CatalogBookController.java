package com.sorsix.librarianapi.api;

import com.sorsix.librarianapi.model.CatalogBook;
import com.sorsix.librarianapi.model.User;
import com.sorsix.librarianapi.repository.UserRepository;
import com.sorsix.librarianapi.security.CustomUserDetails;
import com.sorsix.librarianapi.service.CatalogBookService;
import com.sorsix.librarianapi.service.exceptions.BookNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class CatalogBookController {

    private final Logger logger = LoggerFactory.getLogger(CatalogBookController.class);

    private final CatalogBookService service;

    private final UserRepository userRepository;

    public CatalogBookController(CatalogBookService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

//    @RequestMapping(value = "/login")
//    public void login(@AuthenticationPrincipal CustomUserDetails currentUser) {
//        logger.warn("Hit /api/public/login");
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        return userRepository.findById(currentUser.getId())
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

//    @PostMapping("/login")
//    public void login() {
//        logger.warn("Hit /api/public/login");
//    }

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

    @GetMapping("/books/search")
    public List<CatalogBook> getCatalogBookByTitle(@RequestParam("title") String title) {
        return service.getCatalogBooksByTitle(title);
    }

    //TODO: Refactor into @RequestParam (DONE)
    //TODO(!!!) TEST THIS METHOD        (DONE)
    @GetMapping("/books/similar")
    public List<CatalogBook> getSimilar(@RequestParam("bookId") Long bookId) {
        return service.getSimilar(bookId);
    }

    //TODO(!!!): Add finely grained exception handlers
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void bookNotFoundError(BookNotFound e) {
        logger.warn("bookNotFoundError [{}]", e.toString());
    }

//    @GetMapping("/books/author/{author}")
//    public List<CatalogBook> getCatalogBooksByAuthor(@PathVariable String author) {
//        return service.getCatalogBooksByAuthor(author);
//    }
//
//    @GetMapping("/books/genre/{genre}")
//    public List<CatalogBook> getCatalogBooksByGenre(@PathVariable String genre) {
//        return service.getCatalogBooksByGenre(genre);
//    }
}
