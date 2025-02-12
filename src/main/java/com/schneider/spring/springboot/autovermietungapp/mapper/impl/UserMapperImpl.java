package com.schneider.spring.springboot.autovermietungapp.mapper.impl;

import com.schneider.spring.springboot.autovermietungapp.dto.UserDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.User;
import com.schneider.spring.springboot.autovermietungapp.mapper.UserMapper;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public List<UserDTO> toUserDTOList(List<User> userList) {
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userList) {
            String name = user.getName();
            String email = user.getEmail();


            userDTOList.add(new UserDTO(name, email));
        }
        return userDTOList;

    }

    @Override
    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}