package com.example.demo.controller;

import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    // Register a new user
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestParam String username, @RequestParam String password) {
//        boolean isRegistered = loginService.registerUser(username, password);
//        if (isRegistered) {
//            return ResponseEntity.ok("User registered successfully.");
//        } else {
//            return ResponseEntity.badRequest().body("Username already exists.");
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String username, @RequestParam String password) {
        try {
            boolean isRegistered = loginService.registerUser(username, password);
            if (isRegistered) {
                return ResponseEntity.ok("User registered successfully.");
            } else {
                return ResponseEntity.badRequest().body("Username already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log to Render logs
            return ResponseEntity.status(500).body("Server error: " + e.getMessage());
        }
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean isAuthenticated = loginService.login(username, password);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.badRequest().body("Invalid username or password.");
        }
    }
}
