package com.sorsix.librarianapi.repository;

import com.sorsix.librarianapi.model.CatalogBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogBookRepository extends JpaRepository<CatalogBook, Long> {
    List<CatalogBook> findAllByAuthor_Name(String author);

    List<CatalogBook> findAllByGenre_Name(String genre);

    @Query(value = "select * from (select cb.genre_id from v_catalog_books cb where cb.id = :bookId) gg join v_catalog_books v on gg.genre_id=v.genre_id where v.id !=:bookId", nativeQuery = true)
    List<CatalogBook> findSimilar(@Param("bookId")Long bookId);

    List<CatalogBook> findAllByTitleContainingIgnoreCase(String title);

    @Query(value = "select * from v_most_popular", nativeQuery = true)
    List<CatalogBook> findMostPopular();

    @Query(value = "select * from v_catalog_books", nativeQuery = true)
    List<CatalogBook> findAll();

    @Query(value="select cb.id,cb.title,cb.summary,cb.cover,cb.author_id,cb.genre_id,\n" +
            "       CASE\n" +
            "           when cb.id in (subquery.catalog_book_id) THEN true\n" +
            "           else false\n" +
            "           END\n" +
            "           as available from\n" +
            "    (select ib.catalog_book_id, count(*) NumberOfAvailable\n" +
            "     from available_books_from_inventory ib\n" +
            "     group by ib.catalog_book_id) subquery\n" +
            "\n" +
            "        right join catalog_books cb on cb.id=subquery.catalog_book_id\n" +
            "where cb.id=:bookId",nativeQuery = true)
    Optional<CatalogBook> findByIdWithAvailable(@Param("bookId") Long bookId);
}
