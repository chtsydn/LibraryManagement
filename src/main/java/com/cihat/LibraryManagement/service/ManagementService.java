package com.cihat.LibraryManagement.service;

import com.cihat.LibraryManagement.model.entity.Book;
import com.cihat.LibraryManagement.model.entity.Borrower;
import com.cihat.LibraryManagement.model.repository.BookRepository;
import com.cihat.LibraryManagement.model.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA at 24.02.2023
 *
 * @author Cihat Soydan
 */
@Service
public class ManagementService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowerRepository borrowerRepository;

    public void addBook(String title,String author,String isbn,Integer copies){
        Book book = Book.getBookInstance();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setAvailableCopies(copies);
        bookRepository.save(book);
    }
    public void removeBook(String isbn){
        bookRepository.deleteBookByIsbn(isbn);
    }
    public Book getBook(String isbn){
        Optional<Book> bookOptional = bookRepository.findBookByIsbn(isbn);
        return bookOptional.orElse(null);
    }

    public void addBorrower(String name,String email,String phoneNumber){
        Borrower borrower = Borrower.getBorrowerInstance();
        borrower.setName(name);
        borrower.setEmail(email);
        borrower.setPhoneNumber(phoneNumber);
        borrower.setBookIsbn(null);
        borrowerRepository.save(borrower);
    }
    public void removeBorrower(String email){
        borrowerRepository.deleteBorrowerByEmail(email);
    }
    public Borrower getBorrower(String email){
        Optional<Borrower> borrowerOptional = borrowerRepository.findBorrowerByEmail(email);
        return borrowerOptional.orElse(null);
    }

    public void borrowBook(Long bookId,Long borrowerId){
        Optional<Book> bookOptional = bookRepository.findBookById(bookId);
        Optional<Borrower> borrowerOptional = borrowerRepository.findBorrowerById(borrowerId);
        if (bookOptional.isPresent() && borrowerOptional.isPresent()){
            Book book = bookOptional.get();
            Borrower borrower = borrowerOptional.get();
            if (borrower.getBookIsbn() == null && book.getAvailableCopies()>0){
                borrower.setBookIsbn(book.getIsbn());
                book.setAvailableCopies(book.getAvailableCopies()-1);
                borrowerRepository.save(borrower);
                bookRepository.save(book);
            }
        }
    }

    public void returnBook(Long borrowerId){
        Optional<Borrower> borrowerOptional = borrowerRepository.findBorrowerById(borrowerId);
        String isbn = null;
        if (borrowerOptional.isPresent()){
            Borrower borrower = borrowerOptional.get();
            isbn = borrower.getBookIsbn();
        }
        Optional<Book> bookOptional = isbn==null?Optional.empty():bookRepository.findBookByIsbn(isbn);
        if (bookOptional.isPresent()){
            Book book = bookOptional.get();
            book.setAvailableCopies(book.getAvailableCopies()+1);
            bookRepository.save(book);
        }
    }
}
