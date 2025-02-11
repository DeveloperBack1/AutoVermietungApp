package com.schneider.spring.springboot.autovermietungapp.controller;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.dto.UserDTO;
import com.schneider.spring.springboot.autovermietungapp.service.CarService;
import com.schneider.spring.springboot.autovermietungapp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
