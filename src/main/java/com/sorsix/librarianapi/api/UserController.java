package com.sorsix.librarianapi.api;

import com.sorsix.librarianapi.service.UserService;
import org.springframework.web.bind.annotation.RestController;

//TODO
@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

}
