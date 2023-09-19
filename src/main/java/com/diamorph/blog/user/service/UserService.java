package com.diamorph.blog.user.service;

import com.diamorph.blog.user.dto.UserDto;

public interface UserService {

    UserDto retrieveUserDtoById(int id);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(int id, UserDto userDto);

    void deleteUser(int id);
}
