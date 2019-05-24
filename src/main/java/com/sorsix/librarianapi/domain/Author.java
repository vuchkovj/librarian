package com.sorsix.librarianapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Type(type = "text")
    private String biography;

    @OneToMany(mappedBy = "author")
    @JsonBackReference
    private List<CatalogBook> books;
}
