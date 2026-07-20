package com.careerlens.service;

import com.careerlens.dto.LoginRequest;
import com.careerlens.dto.LoginResponse;
import com.careerlens.dto.UserRegistrationDto;
import com.careerlens.entity.User;
import com.careerlens.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(UserRegistrationDto dto) {

        User user = User.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();

        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request){

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if(user == null){

            return LoginResponse.builder()
                    .message("Email not found")
                    .build();

        }

        if(!user.getPassword().equals(request.getPassword())){

            return LoginResponse.builder()
                    .message("Invalid Password")
                    .build();

        }

        return LoginResponse.builder()
                .message("Login Successful")
                .userId(user.getId())
                .fullName(user.getFullName())
                .build();

    }

}