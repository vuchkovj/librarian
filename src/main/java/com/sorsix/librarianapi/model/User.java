package com.sorsix.librarianapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private String address;

    @OneToMany(mappedBy = "user")
    private List<Lease> leases = new ArrayList<>();

    public void addLease(Lease l) {
        leases.add(l);
        l.setUser(this);
    }
}
