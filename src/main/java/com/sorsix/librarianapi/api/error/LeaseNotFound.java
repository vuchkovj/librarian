package com.sorsix.librarianapi.api.error;

public class LeaseNotFound extends RuntimeException {
    public LeaseNotFound(String message) {
        super(message);
    }
}
