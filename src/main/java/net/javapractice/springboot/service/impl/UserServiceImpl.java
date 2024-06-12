package net.javapractice.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javapractice.springboot.dto.UserDTO;
import net.javapractice.springboot.entity.User;
import net.javapractice.springboot.mapper.UserMapper;
import net.javapractice.springboot.repository.UserRepository;
import net.javapractice.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Override
    public UserDTO createUser(UserDTO userDTO) {

        // Convert userDTO into User JPA Entity
        User user = UserMapper.mapToUser(userDTO);

        User savedUser = userRepository.save(user);

        // Convert User JPA Entity to UserDTO
        UserDTO saveduserDTO = UserMapper.mapToUserDTO(savedUser);

        return saveduserDTO;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.mapToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
