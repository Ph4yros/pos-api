package com.example.POSApi.controller;

import com.example.POSApi.model.LoginRequest;
import com.example.POSApi.model.LoginResponse;
import com.example.POSApi.model.User;
import com.example.POSApi.respository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());

        if (user != null && user.getPassword().equals(request.getPassword())) {
            return new LoginResponse("success", "Giriş başarılı", user.getToken());
        } else {
            return new LoginResponse("error", "Kullanıcı adı veya şifre hatalı", null);
        }
    }
}
