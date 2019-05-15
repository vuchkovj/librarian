package com.sorsix.librarianapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "catalog_books")
public class CatalogBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Type(type = "text")
    private String summary;

    @OneToMany(mappedBy = "catalogBook")
    @JsonIgnore
    private List<InventoryBook> inventoryBooks = new ArrayList<>();

    @ManyToOne
    @JsonManagedReference
    private Author author;

    @ManyToOne
    @JsonManagedReference
    private Genre genre;
}
