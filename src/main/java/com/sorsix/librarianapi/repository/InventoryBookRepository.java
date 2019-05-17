package com.sorsix.librarianapi.repository;

import com.sorsix.librarianapi.model.InventoryBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryBookRepository extends JpaRepository<InventoryBook, Long> {
    @Query(value = "select *\n" +
            "from inventory_books ib\n" +
            "where ib.inventory_number not in\n" +
            "      (select l.inventory_book_number from leases l where l.returned = false)\n" +
            "  and ib.catalog_book_id = :id\n" +
            "limit 1;", nativeQuery = true)
    Optional<InventoryBook> getFirstAvailable(@Param("id") Long id);
}
