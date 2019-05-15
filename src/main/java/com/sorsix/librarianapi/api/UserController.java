package com.sorsix.librarianapi.api;

import com.sorsix.librarianapi.model.User;
import com.sorsix.librarianapi.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
}
