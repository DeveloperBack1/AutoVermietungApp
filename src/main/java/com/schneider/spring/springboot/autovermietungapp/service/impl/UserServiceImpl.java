package com.schneider.spring.springboot.autovermietungapp.service.impl;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.dto.UserDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.entity.User;
import com.schneider.spring.springboot.autovermietungapp.exception.CarsNotExistInDataBaseException;
import com.schneider.spring.springboot.autovermietungapp.exception.UsersNotExistInDataBaseException;
import com.schneider.spring.springboot.autovermietungapp.exception.errorMessages.ErrorMessage;
import com.schneider.spring.springboot.autovermietungapp.mapper.CarMapper;
import com.schneider.spring.springboot.autovermietungapp.mapper.UserMapper;
import com.schneider.spring.springboot.autovermietungapp.repository.CarRepository;
import com.schneider.spring.springboot.autovermietungapp.repository.UserRepository;
import com.schneider.spring.springboot.autovermietungapp.service.CarService;
import com.schneider.spring.springboot.autovermietungapp.service.UserService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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
    }

