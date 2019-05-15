package com.sorsix.librarianapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "leases")
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_of_lease")
    private LocalDateTime timeOfLease = LocalDateTime.now();

    @Column(name = "due_time")
    private LocalDateTime due_time = timeOfLease.plusWeeks(3L);

    private boolean returned;

    @ManyToOne
    @JoinColumn(name = "inventory_book_number")
    private InventoryBook inventoryBook;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addInventoryBook(InventoryBook ib) {
        setInventoryBook(ib);
        ib.getLeases().add(this);
    }
}
