package com.careerlens.controller;

import com.careerlens.dto.LoginRequest;
import com.careerlens.dto.LoginResponse;
import com.careerlens.dto.UserRegistrationDto;
import com.careerlens.entity.User;
import com.careerlens.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody UserRegistrationDto dto){

        return userService.registerUser(dto);

    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){

        return userService.login(request);

    }

}