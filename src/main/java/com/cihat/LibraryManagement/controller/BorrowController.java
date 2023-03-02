package com.cihat.LibraryManagement.controller;

import com.cihat.LibraryManagement.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA at 24.02.2023
 *
 * @author Cihat Soydan
 */
@RestController
@RequestMapping("/api/borrow")
@CrossOrigin("*")
public class BorrowController {
    @Autowired
    private ManagementService managementService;

    @GetMapping("/borrowBook")
    public ResponseEntity<?> borrowBook(
            @RequestParam("bookIsbn") String bookIsbn,
            @RequestParam("borrowerEmail") String borrowerEmail
    )
    {
        try {
            return ResponseEntity.ok().body(managementService.borrowBook(bookIsbn,borrowerEmail));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("The book could not be borrowed.");
        }
    }
    @GetMapping("/returnBook")
    public ResponseEntity<?> returnBook(
            @RequestParam("borrowerEmail") String borrowerEmail
    )
    {
        try {
            managementService.returnBook(borrowerEmail);
            return ResponseEntity.ok().body("The book was returned.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
