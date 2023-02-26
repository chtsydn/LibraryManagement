package com.cihat.LibraryManagement.controller;

import com.cihat.LibraryManagement.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA at 24.02.2023
 *
 * @author Cihat Soydan
 */
@RestController
@RequestMapping("/api/borrow")
public class BorrowController {
    @Autowired
    private ManagementService managementService;

    @GetMapping("/borrowBook")
    public ResponseEntity<?> borrowBook(
            @RequestParam("bookId") Long bookId,
            @RequestParam("borrowerId") Long borrowerId
    )
    {
        try {
            return ResponseEntity.ok().body(managementService.borrowBook(bookId,borrowerId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("The book could not be borrowed.");
        }
    }
    @GetMapping("/returnBook")
    public ResponseEntity<?> returnBook(
            @RequestParam("borrowerId") Long borrowerId
    )
    {
        try {
            managementService.returnBook(borrowerId);
            return ResponseEntity.ok().body("The book was returned.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
