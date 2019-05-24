package com.sorsix.librarianapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "inventoryBook")
    @JsonBackReference
    private List<Lease> leases;
}
