package com.cihat.LibraryManagement.model.repository;

import com.cihat.LibraryManagement.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA at 25.02.2023
 *
 * @author Cihat Soydan
 */

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findBookById(Long id);

    Optional<Book> findBookByIsbn(String isbn);

    void deleteBookByIsbn(String isbn);
}
