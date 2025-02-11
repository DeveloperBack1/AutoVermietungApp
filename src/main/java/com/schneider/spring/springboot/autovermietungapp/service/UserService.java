package com.schneider.spring.springboot.autovermietungapp.service;

import com.schneider.spring.springboot.autovermietungapp.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserDTO> getAllUsers();
}
