package com.cihat.LibraryManagement.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA at 23.02.2023
 *
 * @author Cihat Soydan
 */
@Getter
@Setter
@Table
@Entity
public class Book {
    private static Book bookInstance;
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer availableCopies;

    public static Book getBookInstance(){
        if (bookInstance==null){
            return new Book();
        }
        return bookInstance;
    }
}
