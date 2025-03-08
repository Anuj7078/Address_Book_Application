package com.example.address_book_application.controller;

import com.example.address_book_application.dto.UserDTO;
import com.example.address_book_application.model.User;
import com.example.address_book_application.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j // Logging activate karo
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Fetching all users"); // Info level log
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        log.debug("Fetching user by ID: {}", id); // Debug level log
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        log.info("Creating a new user: {}", userDTO); // Info level log
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<User>> createUsers(@RequestBody List<UserDTO> userDTOList) {
        log.info("Creating multiple users: {}", userDTOList); // Info level log
        return ResponseEntity.ok(userService.saveAllUsers(userDTOList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        log.info("Updating user with ID {}: {}", id, userDTO); // Info level log
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.warn("Deleting user with ID: {}", id); // Warn level log
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}