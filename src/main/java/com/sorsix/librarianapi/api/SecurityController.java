package com.sorsix.librarianapi.api;

import com.sorsix.librarianapi.repository.UserRepository;
import org.springframework.web.bind.annotation.RestController;

//TODO
@RestController
public class SecurityController {

    private final UserRepository userRepository;

    public SecurityController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity<User> currentPrincipal(@AuthenticationPrincipal CustomUserDetails currentUser) {
//        System.out.println(currentUser);
//        return userRepository.findById(currentUser.getId())
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
}
