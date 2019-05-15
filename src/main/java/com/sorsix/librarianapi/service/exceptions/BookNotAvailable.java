package com.sorsix.librarianapi.service.exceptions;

public class BookNotAvailable extends RuntimeException {
    public BookNotAvailable(String message) {
        super(message);
    }
}
