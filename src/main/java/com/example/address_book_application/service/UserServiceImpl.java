package com.example.address_book_application.service;

import com.example.address_book_application.dto.UserDTO;
import com.example.address_book_application.model.User;
import com.example.address_book_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setPincode(userDTO.getPincode());
        user.setPermanentAddress(userDTO.isPermanentAddress());
        user.setUsername(userDTO.getUsername()); // Set username
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encode password
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setPincode(userDTO.getPincode());
        user.setPermanentAddress(userDTO.isPermanentAddress());
        user.setUsername(userDTO.getUsername()); // Update username
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Update password
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> saveAllUsers(List<UserDTO> userDTOList) {
        List<User> users = userDTOList.stream()
                .map(dto -> {
                    User user = new User();
                    user.setName(dto.getName());
                    user.setAddress(dto.getAddress());
                    user.setPincode(dto.getPincode());
                    user.setPermanentAddress(dto.isPermanentAddress());
                    user.setUsername(dto.getUsername()); // Set username
                    user.setPassword(passwordEncoder.encode(dto.getPassword())); // Encode password
                    return user;
                })
                .collect(Collectors.toList());

        return userRepository.saveAll(users);
    }
}