package com.sorsix.librarianapi.repository;

import com.sorsix.librarianapi.model.InventoryBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryBookRepository extends JpaRepository<InventoryBook, Long> {
//    == Find all available inventory books ==
//    select * from inventory_books ib left join leases l on l.inventory_book_number=ib.id where ib.catalog_book_id = :catalogBookId and l.id is null or l.finished = true
}
