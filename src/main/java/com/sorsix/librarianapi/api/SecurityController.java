/*
package com.sorsix.librarianapi.api;
import com.sorsix.librarianapi.domain.User;
import com.sorsix.librarianapi.service.UserService;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/public")
public class SecurityController {
    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }
}

    @GetMapping("/principal")
    public Principal principal(Principal principal) {
        return principal;
    }

    @GetMapping("/user")
    public User user(Principal principal) {
        return userService.getUserForPrincipal(principal);
    }

    @RequestMapping("/logout")
    public boolean logout(HttpServletRequest request, HttpServletResponse response, Principal principal) {
        System.out.println("logout()");
        System.out.println(principal.getName());
        try {
            response.sendRedirect("/api/public/books");
            request.logout();
            return true;
        } catch (ServletException | IOException e) {
            return false;
        }
    }

    private final UserRepository userRepository;

    public SecurityController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @RequestMapping("/login")
    public void login() {
        System.out.println("Hi");
    }
        User user = userService.getUserForPrincipal(principal);
        System.out.println(user.getEmail());
        return userRepository.findByEmail(request.getUserPrincipal().getName())
                .orElseThrow(() -> new UserNotFoundException("User not found " + request.getUserPrincipal().getName()));

    @RequestMapping("/login")
    public User login(HttpServletRequest request) throws UserNotFoundException {
        return userRepository.findByEmail(request.getUserPrincipal().getName())
                .orElseThrow(() -> new UserNotFoundException("User not found " + request.getUserPrincipal().getName()));
    }
*/
