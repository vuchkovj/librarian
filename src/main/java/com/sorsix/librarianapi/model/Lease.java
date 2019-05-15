package com.sorsix.librarianapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "leases")
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_lease")
    private LocalDate dateOfLease;

    @Column(name = "date_to_return")
    private LocalDate dateToReturn;

    @OneToOne
    @JoinColumn(name = "inventory_book_number")
    private InventoryBook inventoryBook;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addInventoryBook(InventoryBook ib) {
        inventoryBook = ib;
        ib.setLease(this);
    }
}
