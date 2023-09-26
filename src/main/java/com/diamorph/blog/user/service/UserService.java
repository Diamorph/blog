package com.diamorph.blog.user.service;

import com.diamorph.blog.user.dto.UserCreateDto;
import com.diamorph.blog.user.dto.UserDto;

public interface UserService {

    UserDto retrieveUserDtoById(int id);

    UserDto createUser(UserCreateDto userDto);

    UserDto updateUser(int id, UserCreateDto userDto);

    void deleteUser(int id);
}
