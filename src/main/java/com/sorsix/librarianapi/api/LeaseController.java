package com.sorsix.librarianapi.api;

import com.sorsix.librarianapi.model.Lease;
import com.sorsix.librarianapi.service.LeaseService;
import com.sorsix.librarianapi.service.exceptions.BookNotAvailable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LeaseController {

    private final Logger logger = LoggerFactory.getLogger(LeaseController.class);

    private final LeaseService service;

    public LeaseController(LeaseService service) {
        this.service = service;
    }

    @GetMapping("/leases")
    public List<Lease> getAllLeases() {
        return this.service.getAllLeases();
    }

    //TODO(!!!): Look at this tomorrow
    @PostMapping("/leases/new")
    public Lease newLease(@RequestBody Map<String, Long> req) {
        return service.newLease(req.get("id"));
    }

//    TODO(!!!): Should this method be moved into User Controller?
//    @GetMapping("/leases")
//    public List<Lease> getAllByUser(@RequestParam(name = "username") String username) {
//        return service.getAllByUser(username);
//    }

    //TODO(!): Look at this tomorrow
    @PostMapping("/update")
    public Lease updateLease(@RequestBody Map<String, Long> req) {
        return service.updateLease(req.get("id"));
    }

    //TODO(!!!): Add finely grained exception handlers
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void newLeaseError(BookNotAvailable e) {
        logger.warn("newLeaseError [{}]", e.toString());
    }
}
