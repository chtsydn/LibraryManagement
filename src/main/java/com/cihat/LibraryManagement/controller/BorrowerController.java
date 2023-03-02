package com.cihat.LibraryManagement.controller;

import com.cihat.LibraryManagement.model.entity.Borrower;
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
@RequestMapping("/api/borrower")
@CrossOrigin("*")
public class BorrowerController {

    @Autowired
    private ManagementService managementService;

    @PostMapping("/addBorrower")
    public ResponseEntity<?> addBorrower(
            @RequestBody Borrower borrower
            )
    {
        try {
            return ResponseEntity.ok().body(managementService.addBorrower(borrower));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/removeBorrower")
    public ResponseEntity<?> removeBorrower(
            @RequestBody String email
    )
    {
        try {
            managementService.removeBorrower(email);
            return ResponseEntity.ok().body("Borrower removed.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getBorrower")
    public ResponseEntity<?> getBorrower(
            @RequestParam("email")String email
    )
    {
        try {
            return ResponseEntity.ok().body(managementService.getBorrower(email));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Borrower not found.");
        }
    }
}
