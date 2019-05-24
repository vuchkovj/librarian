package com.sorsix.librarianapi.repository;

import com.sorsix.librarianapi.domain.Lease;
import com.sorsix.librarianapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Long> {

    List<Lease> getAllByUser_Email(@Param("email") String email);

    List<Lease> findAllByUser(User user);

//    List<Lease> getAllByUser_Id(@Param("id") Long id);

}
