package com.diamorph.blog.user.service;

import com.diamorph.blog.user.dto.UserCreateDto;
import com.diamorph.blog.user.dto.UserDto;
import com.diamorph.blog.user.exception.DuplicateEmailException;
import com.diamorph.blog.user.exception.UserNotFoundException;
import com.diamorph.blog.user.jpa.UserRepository;
import com.diamorph.blog.user.mapper.UserMapper;
import com.diamorph.blog.user.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto retrieveUserDtoById(int id) {
        User user = retrieveUserById(id);
        return convertToDto(user);
    }

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {
        User user = convertToEntity(userCreateDto);
        User savedUser = save(user);
        return convertToDto(savedUser);
    }

    @Override
    public UserDto updateUser(int id, UserCreateDto userCreateDto) {
        User user = retrieveUserById(id);
        User updatedUserData = convertToEntity(userCreateDto);
        user.setEmail(updatedUserData.getEmail());
        user.setFirstName(updatedUserData.getFirstName());
        user.setLastName(updatedUserData.getLastName());
        user.setAge(updatedUserData.getAge());
        User savedUser = null;
        try {
            savedUser = save(user);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                // not the best approach because of security risks, but just for study purpose
                throw new DuplicateEmailException("This email is used by another user");
            }
            throw e;
        }
        return convertToDto(savedUser);
    }

    @Override
    public void deleteUser(int id) {
        validateExistenceById(id);
        userRepository.deleteById(id);
    }

    public User retrieveUserById(int id) {
        Optional<User> user = findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found: " + id);
        }
        return user.get();
    }

    private void validateExistenceById(int id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found: " + id);
        }
    }

    private Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    private User save(User user) {
        return userRepository.save(user);
    }

    private UserDto convertToDto(User user) {return UserMapper.INSTANCE.toUserDto(user); }

    private User convertToEntity(UserCreateDto userDto) {return UserMapper.INSTANCE.toUser(userDto); }
}
