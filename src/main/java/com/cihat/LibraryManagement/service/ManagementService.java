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

    public String addBook(Book book){
        String isbn = book.getIsbn();
        try {
            Book book2 = getBook(isbn);
            book2.setAvailableCopies(book.getAvailableCopies()+ book2.getAvailableCopies());
            bookRepository.save(book2);
            return "The book has already been added. Copies added.";
        }catch (Exception e){
            bookRepository.save(book);
            return "Book added.";
        }
    }
    public void removeBook(String isbn){
        Book book = getBook(isbn);
        if (book!=null){
            bookRepository.delete(book);
        }else {
            throw new IllegalArgumentException("No books found.");
        }
    }
    public Book getBook(String isbn){
        Optional<Book> bookOptional = bookRepository.findBookByIsbn(isbn);
        return bookOptional.orElse(null);
    }

    public String addBorrower(Borrower borrower){
        String email = borrower.getEmail();
        Borrower borrower2 = getBorrower(email);
        if (borrower2==null){
            borrowerRepository.save(borrower);
            return "Borrower added.";
        }else {
            throw new IllegalArgumentException("Borrower can not added.");
        }
    }
    public void removeBorrower(String email){
        Borrower borrower = getBorrower(email);
        if (borrower!=null){
            borrowerRepository.delete(borrower);
        }else {
            throw new IllegalArgumentException("No borrowers found.");
        }
    }
    public Borrower getBorrower(String email){
        Optional<Borrower> borrowerOptional = borrowerRepository.findBorrowerByEmail(email);
        return borrowerOptional.orElse(null);
    }

    public String borrowBook(String bookIsbn,String borrowerEmail){
        Optional<Book> bookOptional = bookRepository.findBookByIsbn(bookIsbn);
        Optional<Borrower> borrowerOptional = borrowerRepository.findBorrowerByEmail(borrowerEmail);
        if (bookOptional.isPresent()){
            Book book = bookOptional.get();
            if (borrowerOptional.isPresent()){
                Borrower borrower = borrowerOptional.get();
                if (borrower.getBookIsbn() == null && book.getAvailableCopies()>0){
                    borrower.setBookIsbn(book.getIsbn());
                    book.setAvailableCopies(book.getAvailableCopies()-1);
                    borrowerRepository.save(borrower);
                    bookRepository.save(book);
                    return "The book was borrowed.";
                }
            }else {
                throw new IllegalArgumentException("Borrower not found.");
            }
        }else {
            throw new IllegalArgumentException("Book not found.");
        }
        return "The book could not be borrowed.";
    }

    public void returnBook(String borrowerEmail){
        Optional<Borrower> borrowerOptional = borrowerRepository.findBorrowerByEmail(borrowerEmail);
        String isbn = null;
        if (borrowerOptional.isPresent()){
            Borrower borrower = borrowerOptional.get();
            isbn = borrower.getBookIsbn();
            Optional<Book> bookOptional = isbn==null?Optional.empty():bookRepository.findBookByIsbn(isbn);
            if (bookOptional.isPresent()){
                Book book = bookOptional.get();
                book.setAvailableCopies(book.getAvailableCopies()+1);
                borrower.setBookIsbn(null);
                borrowerRepository.save(borrower);
                bookRepository.save(book);
            }else {
                throw new IllegalArgumentException("Book not found.");
            }
        }else {
            throw new IllegalArgumentException("Borrower not found.");
        }
    }
}
