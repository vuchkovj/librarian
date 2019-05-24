package com.sorsix.librarianapi.service;

import com.sorsix.librarianapi.domain.User;
import com.sorsix.librarianapi.repository.UserRepository;
import com.sorsix.librarianapi.api.error.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getUserForPrincipal(Principal principal) {
        return repository.findByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("User with id " + principal.getName() + " not found"));
    }

}
