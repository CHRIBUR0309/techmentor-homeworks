package com.example.restful_todo.service;

import com.example.restful_todo.mapper.*;
import com.example.restful_todo.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User getUser(UserId userId, HashedPassword hashedPassword) {
        return this.userMapper.getUser(userId, hashedPassword);
    }

    public User createUser(UserId userId, HashedPassword hashedPassword) {
        return this.userMapper.createUser(userId, hashedPassword);
    }

    public User updateUser(UserId userId, HashedPassword hashedPasswordBefore,
            HashedPassword hashedPasswordAfter) {
        return this.userMapper.updateUser(userId, hashedPasswordBefore, hashedPasswordAfter);
    }

    public void deleteUser(UserId userId, HashedPassword hashedPassword) {
        this.userMapper.deleteUser(userId, hashedPassword);
    }
}
