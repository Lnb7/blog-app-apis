package com.blogappapis.controller;

import com.blogappapis.mapper.ApiResponse;
import com.blogappapis.mapper.UserMapper;
import com.blogappapis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserMapper> createUser(@Valid @RequestBody UserMapper userMapper){
        UserMapper user = this.userService.createUser(userMapper);
        return new ResponseEntity<UserMapper>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserMapper> updateUser(@RequestBody UserMapper userMapper,@PathVariable Integer userId){
        UserMapper user = this.userService.updateUser(userMapper,userId);
        return new ResponseEntity<UserMapper>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return  new ResponseEntity(new ApiResponse("user deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserMapper>> getAllUser(){
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserMapper> getUserById(@PathVariable Integer userId){
        UserMapper userById = this.userService.getUserById(userId);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }
}
