package com.example.POSApi.service;

import com.example.POSApi.model.User;
import com.example.POSApi.respository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public boolean checkPassword(User user, String plainPassword) {
        if (user == null) return false;
        return BCrypt.checkpw(plainPassword, user.getPassword());
    }
}
