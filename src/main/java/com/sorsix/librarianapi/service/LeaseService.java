package com.sorsix.librarianapi.service;

import com.sorsix.librarianapi.repository.LeaseRepository;
import com.sorsix.librarianapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LeaseService {
    private final LeaseRepository leaseRepository;
    private final UserRepository userRepository;
    private final InventoryManagerService inventoryManagerService;

    public LeaseService(LeaseRepository leaseRepository,
                        UserRepository userRepository,
                        InventoryManagerService inventoryManagerService) {
        this.leaseRepository = leaseRepository;
        this.userRepository = userRepository;
        this.inventoryManagerService = inventoryManagerService;
    }

//    public void newLease(Long catalogBookId) { }
}
