package com.sorsix.librarianapi.service;

import com.sorsix.librarianapi.api.error.LeaseNotFound;
import com.sorsix.librarianapi.domain.InventoryBook;
import com.sorsix.librarianapi.domain.Lease;
import com.sorsix.librarianapi.domain.User;
import com.sorsix.librarianapi.repository.InventoryBookRepository;
import com.sorsix.librarianapi.repository.LeaseRepository;
import com.sorsix.librarianapi.api.error.BookNotAvailable;
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

    public List<Lease> getAllByUserEmail(String username) {
        return leaseRepository.getAllByUser_Email(username);
    }

    public List<Lease> getAllByUser(User user) {
        return leaseRepository.findAllByUser(user);
    }

    //@Transactional
    public Lease newLease(Long catalogBookId, User user) {
        InventoryBook inventoryBook = inventoryBookRepository
                .getFirstAvailable(catalogBookId)
                .orElseThrow(() -> new BookNotAvailable("Book [" + catalogBookId + "] not available"));
        Lease lease = new Lease(inventoryBook, user);
        return leaseRepository.save(lease);
    }

    //@Transactional
    public Lease updateLeaseReturned(Long id) {
        Lease lease = leaseRepository.findById(id)
                .orElseThrow(() -> new LeaseNotFound("Could not find lease [" + id + "]"));
        lease.setReturned(true);
        return lease;
    }
}
