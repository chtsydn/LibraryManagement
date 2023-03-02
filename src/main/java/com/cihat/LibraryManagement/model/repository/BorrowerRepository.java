package com.cihat.LibraryManagement.model.repository;

import com.cihat.LibraryManagement.model.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA at 25.02.2023
 *
 * @author Cihat Soydan
 */
public interface BorrowerRepository extends JpaRepository<Borrower,Long> {

    Optional<Borrower> findBorrowerByEmail(String email);
}
