package com.sorsix.librarianapi.api.error;

public class BookNotAvailable extends RuntimeException {
    public BookNotAvailable(String message) {
        super(message);
    }
}
