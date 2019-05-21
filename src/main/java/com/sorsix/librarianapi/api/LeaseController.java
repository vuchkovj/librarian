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

@CrossOrigin({"*"})
@RestController
@RequestMapping("/leases")
public class LeaseController {

    private final Logger logger = LoggerFactory.getLogger(LeaseController.class);

    private final LeaseService service;

    public LeaseController(LeaseService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Lease> getAllLeases(){
        return this.service.getAllLeases();
    }

    @GetMapping("/user")
    public List<Lease> getAllByUser(@RequestParam(name = "username") String username) {
        return service.getAllByUser(username);
    }

    @PostMapping("/new")
    public Lease newLease(@RequestBody Map<String, Long> req) {
        System.out.println("New Lease request");
        return service.newLease(req.get("id"));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void onNewLeaseError(BookNotAvailable e) {
        logger.warn("onNewLeaseError [{}]", e.toString());
    }

}
