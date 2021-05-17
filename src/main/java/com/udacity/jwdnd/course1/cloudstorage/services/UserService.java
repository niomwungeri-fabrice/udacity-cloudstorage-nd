package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMappers;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
    private UserMappers userMapper;
    private HashService hashService;

    public UserService(UserMappers userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public int createUser(User user){

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);

        return userMapper.createUser(new User(user.getUsername(), encodedSalt, hashedPassword, user.getFirstname(), user.getLastname()));
    }

    public boolean isUserExist(String username) {
       return userMapper.getUser(username) == null;
    }

    public User getUser(String username){
        return userMapper.getUser(username);
    }
}
