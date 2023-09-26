package com.diamorph.blog.user.mapper;

import com.diamorph.blog.user.dto.UserCreateDto;
import com.diamorph.blog.user.dto.UserDto;
import com.diamorph.blog.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "name", source = "firstName")
    UserDto toUserDto(User user);

    @Mapping(target = "firstName", source = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "posts", ignore = true)
    User toUser(UserCreateDto userCreateDto);

}
