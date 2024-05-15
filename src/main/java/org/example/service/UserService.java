package org.example.service;

import org.example.entities.User;
import org.example.logger.AppLogger;
import org.example.repositories.UserRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRestRepository userRestRepository;

    public User signUpUser(User user){
        String hashedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);
        try{
            User newUser = userRestRepository.save(user);
            return newUser;
        } catch(Error e){
            AppLogger.logError("Error Creating A New User",e);
        }

        return user;
    }

    public boolean loginUser(String userName,String password){
        User user = userRestRepository.findByuserName(userName);
        if(user != null){
            String hashedPassword = user.getPassword();
            return  new BCryptPasswordEncoder().matches(password,hashedPassword);
        } else {
            return false;
        }

    }
}
