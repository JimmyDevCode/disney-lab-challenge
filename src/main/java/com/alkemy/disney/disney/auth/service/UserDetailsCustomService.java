package com.alkemy.disney.disney.auth.service;

import com.alkemy.disney.disney.auth.dto.UserDto;
import com.alkemy.disney.disney.auth.entity.UserEntity;
import com.alkemy.disney.disney.auth.exception.UsernameExistsException;
import com.alkemy.disney.disney.auth.repository.UserRepository;
import com.alkemy.disney.disney.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    EmailServiceImpl emailService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)  {
        UserEntity userEntity = this.userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameExistsException("Username or password not found");
        }

        return User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(Collections.emptyList())
                .build();
                //User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }
    public boolean verifyUsernameExists(String username) {
        UserEntity userEntity = this.userRepository.findByUsername(username);
        if (userEntity != null) {
            throw new UsernameExistsException("Username already exists");
        }
        return userEntity == null;
    }

    public boolean save(UserDto userDto) {
        if (verifyUsernameExists(userDto.getUsername()));

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity = this.userRepository.save(userEntity);
        if (userEntity != null) {
            this.emailService.sendWelcomeEmailTo(userEntity.getUsername());
        }
        return userEntity != null;
    }
}


