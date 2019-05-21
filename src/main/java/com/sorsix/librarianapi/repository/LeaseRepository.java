package com.sorsix.librarianapi.repository;

import com.sorsix.librarianapi.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Long> {

    List<Lease> getAllByUser_Username(@Param("username") String username);

}
