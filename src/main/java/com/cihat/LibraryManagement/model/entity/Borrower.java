package com.cihat.LibraryManagement.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * Created with IntelliJ IDEA at 24.02.2023
 *
 * @author Cihat Soydan
 */
@Getter
@Setter
public class Borrower {
    private static Borrower borrowerInstance;
    @Id
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

    public static Borrower getBorrowerInstance(){
        if (borrowerInstance==null){
            return new Borrower();
        }
        return borrowerInstance;
    }
}
