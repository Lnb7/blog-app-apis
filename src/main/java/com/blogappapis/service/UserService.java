package com.blogappapis.service;

import com.blogappapis.entity.User;
import com.blogappapis.mapper.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserMapper createUser(UserMapper user);

    UserMapper updateUser(UserMapper userMapper, Integer userId);

    UserMapper getUserById(Integer userId);

    List<UserMapper> getAllUsers();

    void deleteUser(Integer userId);

}
