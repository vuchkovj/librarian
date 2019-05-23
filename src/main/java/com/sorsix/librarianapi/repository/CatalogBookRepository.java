package com.sorsix.librarianapi.repository;

import com.sorsix.librarianapi.model.CatalogBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;

@Repository
public interface CatalogBookRepository extends JpaRepository<CatalogBook, Long> {
    List<CatalogBook> findAllByAuthor_Name(String author);

    List<CatalogBook> findAllByGenre_Name(String genre);

    List<CatalogBook> findAllByTitleContainingIgnoreCase(String title);

    @Query(value = "select * from (select cb.genre_id from v_catalog_books cb where cb.id = :bookId) gg join v_catalog_books v on gg.genre_id = v.genre_id where v.id != :bookId", nativeQuery = true)
    List<CatalogBook> findSimilar(@Param("bookId")Long bookId);

    @Query(value = "select * from v_most_popular", nativeQuery = true)
    List<CatalogBook> findMostPopular();

    @Query(value = "select * from v_catalog_books", nativeQuery = true)
    List<CatalogBook> findAll();
}
