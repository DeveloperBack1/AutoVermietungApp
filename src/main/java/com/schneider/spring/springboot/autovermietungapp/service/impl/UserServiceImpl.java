package com.schneider.spring.springboot.autovermietungapp.service.impl;

import com.schneider.spring.springboot.autovermietungapp.dto.UserDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.User;
import com.schneider.spring.springboot.autovermietungapp.exception.UsersNotExistInDataBaseException;
import com.schneider.spring.springboot.autovermietungapp.exception.errorMessages.ErrorMessage;
import com.schneider.spring.springboot.autovermietungapp.mapper.UserMapper;
import com.schneider.spring.springboot.autovermietungapp.repository.UserRepository;
import com.schneider.spring.springboot.autovermietungapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    @Lazy
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserService userService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> list = userRepository.findAll();

        if (list.isEmpty()) {
            throw new UsersNotExistInDataBaseException(ErrorMessage.USERS_NOT_EXIST_IN_DATABASE);
        }
        return userMapper.toUserDTOList(list);
    }

    @Override
    public List<User> findUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public User createUser(UserDTO userDTO) {
        return userRepository.save(userMapper.toUser(userDTO));
    }




    @Override
    public User deleteUser(UserDTO userDTO) {
        return userService.deleteUser(userDTO);
    }

    @Override
    public UserDTO getByEmail(String email) {
        return null;
    }

    @Override
    public UserDTO insertUser(UserDTO userCredentialsDto) {
        return null;
    }
}