package com.sorsix.librarianapi.api;

import com.sorsix.librarianapi.service.LeaseService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/leases")
public class LeaseController {
    private final LeaseService service;

    public LeaseController(LeaseService service) {
        this.service = service;
    }

    @PostMapping("/new")
    public void newLease(@RequestBody Map<String, Long> req) {
        service.newLease(req.get("id"));
    }
}
