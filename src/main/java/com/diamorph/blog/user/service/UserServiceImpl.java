package com.diamorph.blog.user.service;

import com.diamorph.blog.jpa.UserRepository;
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
    public UserDto createUser(UserDto userDto) {
        User user = convertToEntity(userDto);
        User savedUser = save(user);
        return convertToDto(savedUser);
    }

    @Override
    public UserDto updateUser(int id, UserDto userDto) {
        validateExistenceById(id);
        User user = convertToEntity(userDto);
        user.setId(id);
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

    private User retrieveUserById(int id) {
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

    private User convertToEntity(UserDto userDto) {return modelMapper.map(userDto, User.class); }

}
