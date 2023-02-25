package com.cihat.LibraryManagement.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA at 24.02.2023
 *
 * @author Cihat Soydan
 */
@Getter
@Setter
@Table
@Entity
public class Borrower {
    private static Borrower borrowerInstance;
    @Id
    @SequenceGenerator(
            name = "borrower_sequence",
            sequenceName = "borrower_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "borrower_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String bookIsbn;

    public static Borrower getBorrowerInstance(){
        if (borrowerInstance==null){
            return new Borrower();
        }
        return borrowerInstance;
    }
}
