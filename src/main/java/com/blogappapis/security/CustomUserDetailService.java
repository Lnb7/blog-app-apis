package com.blogappapis.security;

import com.blogappapis.entity.User;
import com.blogappapis.exception.ResourceNotFoundException;
import com.blogappapis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database
        User user = this.userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Email", username));
        return user;
    }
}
