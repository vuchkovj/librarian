package com.sorsix.librarianapi.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "inventory_books")
public class InventoryBook {
    @Id
    @Column(name = "inventory_number")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "catalog_book_id")
    private CatalogBook catalogBook;

    @OneToOne(mappedBy = "inventoryBook")
    private Lease lease;
}
