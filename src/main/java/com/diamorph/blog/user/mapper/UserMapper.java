package com.diamorph.blog.user.mapper;

import com.diamorph.blog.user.dto.UserDto;
import com.diamorph.blog.user.model.User;
import org.mapstruct.factory.Mappers;

public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);
}
