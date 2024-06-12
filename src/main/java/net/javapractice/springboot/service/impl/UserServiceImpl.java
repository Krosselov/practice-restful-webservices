package net.javapractice.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javapractice.springboot.dto.UserDTO;
import net.javapractice.springboot.entity.User;
import net.javapractice.springboot.mapper.AutoUserMapper;
import net.javapractice.springboot.mapper.UserMapper;
import net.javapractice.springboot.repository.UserRepository;
import net.javapractice.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        // Convert userDTO into User JPA Entity
        // User user = UserMapper.mapToUser(userDTO);

        // User user = modelMapper.map(userDTO, User.class);

        User user = AutoUserMapper.MAPPER.mapToUser(userDTO);

        User savedUser = userRepository.save(user);

        // Convert User JPA Entity to UserDTO
        // UserDTO savedUserDTO = UserMapper.mapToUserDTO(savedUser);

        // UserDTO savedUserDTO = modelMapper.map(savedUser, UserDTO.class);

        UserDTO savedUserDTO = AutoUserMapper.MAPPER.mapToUserDTO(savedUser);

        return savedUserDTO;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        // return UserMapper.mapToUserDTO(user);
        // return modelMapper.map(user, UserDTO.class);
        return AutoUserMapper.MAPPER.mapToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
/*        return users.stream().map(UserMapper::mapToUserDTO)
                .collect(Collectors.toList());*/

//        return users.stream().map((user)-> modelMapper.map(user, UserDTO.class))
//                .collect(Collectors.toList());

        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        // return UserMapper.mapToUserDTO(updatedUser);
        // return modelMapper.map(updatedUser, UserDTO.class);
        return AutoUserMapper.MAPPER.mapToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
