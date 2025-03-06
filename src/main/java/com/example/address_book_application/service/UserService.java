package com.example.address_book_application.service;

import com.example.address_book_application.dto.UserDTO;
import com.example.address_book_application.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(UserDTO userDTO);
    User updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    List<User> saveAllUsers(List<UserDTO> userDTOList); // New method
}