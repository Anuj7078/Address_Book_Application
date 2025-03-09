package com.example.address_book_application.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private String address;
    private int pincode;
    private boolean isPermanentAddress;
    private String username; // Add username field
    private String password; // Add password field
}