package com.sorsix.librarianapi.repository;

import com.sorsix.librarianapi.model.CatalogBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogBookRepository extends JpaRepository<CatalogBook, Long> {
    List<CatalogBook> findAllByAuthor_Name(String author);

    List<CatalogBook> findAllByGenre_Name(String genre);

    @Query(value = "select * from v_catalog_books cb join genres g on g.id = cb.genre_id where cb.id = :bookId", nativeQuery = true)
    List<CatalogBook> findSimilar(@Param("bookId") Long bookId);

    List<CatalogBook> findAllByTitleContainingIgnoreCase(String title);

    @Query(value = "select * from v_most_popular", nativeQuery = true)
    List<CatalogBook> findMostPopular();

    @Query(value = "select * from v_catalog_books", nativeQuery = true)
    List<CatalogBook> findAll();
}
