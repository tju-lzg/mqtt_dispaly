package com.example.demo.Service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    void insert(User user);
    void delete(int id);
}
