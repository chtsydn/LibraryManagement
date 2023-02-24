package com.cihat.LibraryManagement.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * Created with IntelliJ IDEA at 23.02.2023
 *
 * @author Cihat Soydan
 */
@Getter
@Setter
public class Book {
    private static Book bookInstance;
    @Id
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
