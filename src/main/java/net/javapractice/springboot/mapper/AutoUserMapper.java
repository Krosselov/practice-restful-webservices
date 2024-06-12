package net.javapractice.springboot.mapper;

import net.javapractice.springboot.dto.UserDTO;
import net.javapractice.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDTO mapToUserDTO(User user);

    User mapToUser(UserDTO userDTO);
}
