package com.sorsix.librarianapi.service;

import com.sorsix.librarianapi.model.Lease;
import com.sorsix.librarianapi.repository.InventoryBookRepository;
import com.sorsix.librarianapi.repository.LeaseRepository;
import com.sorsix.librarianapi.service.exceptions.BookNotAvailable;
import org.springframework.stereotype.Service;

@Service
public class LeaseService {
    private final LeaseRepository leaseRepository;
    private final InventoryBookRepository inventoryBookRepository;

    public LeaseService(LeaseRepository leaseRepository,
                        InventoryBookRepository inventoryBookRepository) {
        this.leaseRepository = leaseRepository;
        this.inventoryBookRepository = inventoryBookRepository;
    }

    public Lease newLease(Long catalogBookId) {
        return inventoryBookRepository.getFirstAvailable(catalogBookId)
                .map(inventoryBook -> {
                    Lease l = new Lease();
                    l.addInventoryBook(inventoryBook);
                    return leaseRepository.save(l);
                }).orElseThrow(BookNotAvailable::new);
    }
}
