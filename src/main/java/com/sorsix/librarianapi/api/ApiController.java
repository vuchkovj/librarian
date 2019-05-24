package com.sorsix.librarianapi.api;

import com.sorsix.librarianapi.domain.Lease;
import com.sorsix.librarianapi.domain.User;
import com.sorsix.librarianapi.service.LeaseService;
import com.sorsix.librarianapi.api.error.BookNotAvailable;
import com.sorsix.librarianapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * API Rest Controller
 *
 * Only users who have User || Admin roles can access these api endpoints
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    private final Logger logger = LoggerFactory.getLogger(ApiController.class);
    private final UserService userService;
    private final LeaseService leaseService;

    public ApiController(UserService userService, LeaseService leaseService) {
        this.userService = userService;
        this.leaseService = leaseService;
    }

//    @RequestMapping("/logout")
//    public void logout() {
//        System.out.println("/logout");
//    }

    //This method shall be used by ROLE_ADMIN to get all leases
    @GetMapping("/leases")
    public List<Lease> getAllLeases() {
        return leaseService.getAllLeases();
    }

    //This method shall be used by ROLE_ADMIN to update lease's returned status
    @PostMapping("/leases/update")
    public Lease updateLeaseReturned(@RequestBody Map<String, Long> leaseId) {
        return leaseService.updateLeaseReturned(leaseId.get("id"));
    }

    //This method shall be used by ROLE_ADMIN to get single user's leases
    @GetMapping("/leases/user")
    public List<Lease> getLeasesByUser(@RequestParam("email") String email) {
        return leaseService.getAllByUserEmail(email);
    }

    //This method shall be used by ROLE_USER to make new lease
    //User will send catalog_book_id
    @PostMapping("/leases/new")
    public Lease newLease(@RequestBody Map<String, Long> bookId, Principal principal) {
        //TODO Set lease_user_id to be currently logged in user's id -> DONE
        User user = userService.getUserForPrincipal(principal);
        return leaseService.newLease(bookId.get("id"), user);
    }

    //This method shall be used by ROLE_USER to get their leases
    //System.out.printf("%d %s", user.getId(), user.getEmail()); <-- Print currently logged user
    @GetMapping("/leases/my")
    public List<Lease> getLeases(Principal principal) {
        User user = userService.getUserForPrincipal(principal);
        return leaseService.getAllByUser(user);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void onNewLeaseError(BookNotAvailable e) {
        logger.warn("newLeaseError [{}]", e.toString());
    }
}
