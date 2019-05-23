package com.sorsix.librarianapi.service;

import com.sorsix.librarianapi.model.Lease;
import com.sorsix.librarianapi.repository.InventoryBookRepository;
import com.sorsix.librarianapi.repository.LeaseRepository;
import com.sorsix.librarianapi.service.exceptions.BookNotAvailable;
import com.sorsix.librarianapi.service.exceptions.FailedToUpdateLeaseException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseService {

    private final LeaseRepository leaseRepository;
    private final InventoryBookRepository inventoryBookRepository;

    public LeaseService(LeaseRepository leaseRepository,
                        InventoryBookRepository inventoryBookRepository) {
        this.leaseRepository = leaseRepository;
        this.inventoryBookRepository = inventoryBookRepository;
    }

    public List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }

    public Lease newLease(Long catalogBookId) {
        return inventoryBookRepository.getFirstAvailable(catalogBookId)
                .map(inventoryBook -> {
                    Lease l = new Lease();
                    l.addInventoryBook(inventoryBook);
                    return leaseRepository.save(l);
                })
                .orElseThrow(BookNotAvailable::new);
    }

    //TODO
    public List<Lease> getAllByUser(String username) {
        return leaseRepository.getAllByUser_Email(username);
    }

    public Lease updateLease(Long id) {
        return leaseRepository.findById(id)
                .map(lease -> {
                    lease.setReturned(true);
                    return leaseRepository.save(lease);
                })
                .orElseThrow(FailedToUpdateLeaseException::new);
    }
}
