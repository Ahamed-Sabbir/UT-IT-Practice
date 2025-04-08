package com.sabbir.service;

import com.sabbir.model.User;
import com.sabbir.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public User findByEmail(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("No user found with email: " + email);
        }
        return user;
    }
    public User findById(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("No user found with id: " + id);
        }
        return user;
    }
    public User save(User user) {
        User getUser = userRepo.findById(user.getId()).orElse(null);
        if (getUser != null) {
            throw new RuntimeException("User already exists with id: " + user.getId());
        }
        return userRepo.save(user);
    }
    public User update(User user) {
        User getUser = userRepo.findById(user.getId()).orElse(null);
        if (getUser == null) {
            throw new RuntimeException("User not exists with id: " + user.getId());
        }
        getUser.setEmail(user.getEmail());
        getUser.setName(user.getName());
        getUser.setAge(user.getAge());
        return userRepo.save(getUser);
    }
}
