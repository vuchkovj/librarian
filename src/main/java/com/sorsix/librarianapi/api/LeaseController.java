package com.sorsix.librarianapi.api;

import com.sorsix.librarianapi.service.LeaseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lease")
public class LeaseController {
    private final LeaseService service;

    public LeaseController(LeaseService service) {
        this.service = service;
    }

//    @PostMapping("/new}")
//    public void newLease(@RequestBody Long catalogBookId) {
//        service.newLease(catalogBookId);
//    }
}
