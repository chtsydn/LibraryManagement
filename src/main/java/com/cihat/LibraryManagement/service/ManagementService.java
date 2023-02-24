package com.cihat.LibraryManagement.service;

import com.cihat.LibraryManagement.model.entity.Book;
import com.cihat.LibraryManagement.model.entity.Borrower;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA at 24.02.2023
 *
 * @author Cihat Soydan
 */
@Service
public class ManagementService {

    public void addBook(String title,String author,String isbn,Integer copies){

    }
    public void removeBook(String isbn){

    }
    public Book getBook(String isbn){
        return Book.getBookInstance();
    }

    public void addBorrower(String name,String email,String phoneNumber){

    }
    public void removeBorrower(String email){

    }
    public Borrower getBorrower(String email){
        return Borrower.getBorrowerInstance();
    }

    public void borrowBook(Long bookId,Long borrowerId){

    }

    public void returnBook(Long bookId,Long borrowerId){

    }
}
