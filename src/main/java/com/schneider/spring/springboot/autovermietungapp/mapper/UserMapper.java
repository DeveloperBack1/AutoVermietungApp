package com.schneider.spring.springboot.autovermietungapp.mapper;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.dto.UserDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.entity.User;

import java.util.List;

public interface UserMapper {
    List<UserDTO> toUserDTOList(List<User> userList);
    User toUser(UserDTO userDTO);
}
