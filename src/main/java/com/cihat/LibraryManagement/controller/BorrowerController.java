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
@RequestMapping("/api/borrower")
public class BorrowerController {

    @Autowired
    private ManagementService managementService;

    @PostMapping("/addBorrower")
    public ResponseEntity<?> addBorrower(
            @RequestBody String name,
            @RequestBody String email,
            @RequestBody String phoneNumber
    )
    {
        try {
            managementService.addBorrower(name,email,phoneNumber);
            return ResponseEntity.ok().body("Borrower added.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Borrower can not added.");
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
            return ResponseEntity.badRequest().body("Borrower can not removed.");
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
