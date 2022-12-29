package com.blogappapis.service.Impl;

import com.blogappapis.entity.User;
import com.blogappapis.exception.ResourceNotFoundException;
import com.blogappapis.mapper.UserMapper;
import com.blogappapis.repositories.UserRepository;
import com.blogappapis.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserMapper createUser(UserMapper userMapper) {
        User user = mapperToUser(userMapper);
        User saveUser = userRepository.save(user);
        return userToMapper(saveUser);
    }

    @Override
    public UserMapper updateUser(UserMapper userMapper,Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userMapper.getName());
        user.setEmail(userMapper.getEmail());
        user.setPassword(user.getPassword());
        user.setAbout(user.getAbout());

        User saveUser = userRepository.save(user);
        UserMapper saveUserMapper = userToMapper(saveUser);
        return saveUserMapper;
    }

    @Override
    public UserMapper getUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return this.userToMapper(user);
    }

    @Override
    public List<UserMapper> getAllUsers() {
        List<User> allUser = this.userRepository.findAll();

        List<UserMapper> userMapperList = allUser.stream().map(user -> this.userToMapper(user)).collect(Collectors.toList());
        return userMapperList;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        this.userRepository.deleteById(userId);
    }

    private User mapperToUser(UserMapper userMapper){
        User tempUser= this.modelMapper.map(userMapper, User.class );
        return tempUser;
    }

    private UserMapper userToMapper(User user){
        UserMapper userMapper = this.modelMapper.map(user, UserMapper.class);
        return userMapper;
    }
}
