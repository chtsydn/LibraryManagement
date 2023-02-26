package com.cihat.LibraryManagement.controller;

import com.cihat.LibraryManagement.model.entity.Book;
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
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private ManagementService managementService;

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(
            @RequestBody Book book
            )
    {
        try {
            return ResponseEntity.ok().body(managementService.addBook(book));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/removeBook")
    public ResponseEntity<?> removeBook(
            @RequestBody String isbn
    )
    {
        try {
            managementService.removeBook(isbn);
            return ResponseEntity.ok().body("Book removed.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getBook")
    public ResponseEntity<?> getBook(
            @RequestParam("isbn")String isbn
    )
    {
        try {
            return ResponseEntity.ok().body(managementService.getBook(isbn));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
