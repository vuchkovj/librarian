package com.sorsix.librarianapi.service;

import com.sorsix.librarianapi.repository.CatalogBookRepository;
import com.sorsix.librarianapi.repository.InventoryBookRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryManagerService {
    private final CatalogBookRepository catalogBookRepository;
    private final InventoryBookRepository inventoryBookRepository;

    public InventoryManagerService(CatalogBookRepository catalogBookRepository,
                                   InventoryBookRepository inventoryBookRepository) {
        this.catalogBookRepository = catalogBookRepository;
        this.inventoryBookRepository = inventoryBookRepository;
    }
}
