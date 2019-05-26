package com.sorsix.librarianapi.api;

import com.sorsix.librarianapi.domain.User;
import com.sorsix.librarianapi.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class SecurityController {
    private final UserService service;

    public SecurityController(UserService service) {
        this.service = service;
    }

    @RequestMapping("/login")
    public User login(Principal principal) {
        return service.getUserForPrincipal(principal);
    }

    @RequestMapping("/logout")
    public boolean logout() {
        return true;
    }

    @RequestMapping("/user")
    public User user(Principal principal) {
        return service.getUserForPrincipal(principal);
    }
}
