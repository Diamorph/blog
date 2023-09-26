package com.diamorph.blog.user.service;

import com.diamorph.blog.user.dto.UserCreateDto;
import com.diamorph.blog.user.jpa.UserRepository;
import com.diamorph.blog.user.dto.UserDto;
import com.diamorph.blog.user.exception.DuplicateEmailException;
import com.diamorph.blog.user.exception.UserNotFoundException;
import com.diamorph.blog.user.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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

    private UserDto convertToDto(User user) {return modelMapper.map(user, UserDto.class); }

    private User convertToEntity(UserCreateDto userDto) {return modelMapper.map(userDto, User.class); }
}
