package com.cihat.LibraryManagement.controller;

import com.cihat.LibraryManagement.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/borrowBook")
    public ResponseEntity<?> borrowBook(
            @RequestBody Long bookId,
            @RequestBody Long borrowerId
    )
    {
        try {
            managementService.borrowBook(bookId,borrowerId);
            return ResponseEntity.ok().body("The book was loaned.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("The book could not be loaned.");
        }
    }
    @PostMapping("/returnBook")
    public ResponseEntity<?> returnBook(
            @RequestBody Long bookId,
            @RequestBody Long borrowerId
    )
    {
        try {
            managementService.returnBook(bookId,borrowerId);
            return ResponseEntity.ok().body("The book was returned.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("The book could not be returned.");
        }
    }
}
