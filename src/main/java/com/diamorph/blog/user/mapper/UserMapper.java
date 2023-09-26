package com.diamorph.blog.user.mapper;

import com.diamorph.blog.user.dto.UserCreateDto;
import com.diamorph.blog.user.dto.UserDto;
import com.diamorph.blog.user.model.User;
import org.mapstruct.factory.Mappers;

public interface UserMapper {

    User toUser(UserDto userDto);

    User toUser(UserCreateDto userCreateDto);

    UserDto toUserDto(User user);
}
