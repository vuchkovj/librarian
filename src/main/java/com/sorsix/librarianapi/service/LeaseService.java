package com.sorsix.librarianapi.service;

import com.sorsix.librarianapi.model.InventoryBook;
import com.sorsix.librarianapi.model.Lease;
import com.sorsix.librarianapi.repository.InventoryBookRepository;
import com.sorsix.librarianapi.repository.LeaseRepository;
import com.sorsix.librarianapi.repository.UserRepository;
import com.sorsix.librarianapi.service.exceptions.BookNotAvailable;
import org.springframework.stereotype.Service;

@Service
public class LeaseService {
    private final LeaseRepository leaseRepository;
    private final UserRepository userRepository;
    private final InventoryBookRepository inventoryBookRepository;
    private final InventoryManagerService inventoryManagerService;

    public LeaseService(LeaseRepository leaseRepository,
                        UserRepository userRepository,
                        InventoryBookRepository inventoryBookRepository,
                        InventoryManagerService inventoryManagerService) {
        this.leaseRepository = leaseRepository;
        this.userRepository = userRepository;
        this.inventoryBookRepository = inventoryBookRepository;
        this.inventoryManagerService = inventoryManagerService;
    }

    public void newLease(Long catalogBookId) {
        inventoryBookRepository
                .getFirstAvailableByCatalogBookId(catalogBookId)
                .map(ib -> {
                    Lease l = new Lease();
                    l.addInventoryBook(ib);
                    return leaseRepository.save(l);
                }).orElseThrow(BookNotAvailable::new);
//        InventoryBook ib = inventoryBookRepository.
//                getFirstAvailableByCatalogBookId(catalogBookId);
//        Lease l = new Lease();
//        l.addInventoryBook(ib);
//        leaseRepository.save(l);
    }
}
