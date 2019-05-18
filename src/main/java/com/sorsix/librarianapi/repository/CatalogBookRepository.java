package com.sorsix.librarianapi.repository;

import com.sorsix.librarianapi.model.CatalogBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogBookRepository extends JpaRepository<CatalogBook, Long> {
    List<CatalogBook> findAllByAuthor_Name(String author);

    List<CatalogBook> findAllByGenre_Name(String genre);

    List<CatalogBook> findAllByTitle(String title);

    @Query(value = "select * from v_catalog_books", nativeQuery = true)
    List<CatalogBook> findAll();
//    @Query(value = "select distinct cb.id, cb.summary, cb.title, cb.author_id, cb.genre_id from\n" +
//            "catalog_books cb left join inventory_books ib on cb.id = ib.catalog_book_id\n" +
//            "left join leases l on ib.inventory_number = l.inventory_book_number\n" +
//            "where l.id is null", nativeQuery = true)
//    List<CatalogBook> findAllAvailable();
}
