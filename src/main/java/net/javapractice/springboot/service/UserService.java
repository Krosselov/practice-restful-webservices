package net.javapractice.springboot.service;

import net.javapractice.springboot.dto.UserDTO;
import net.javapractice.springboot.entity.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO getUserById(Long userId);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(UserDTO user);

    void deleteUser(Long id);
}
