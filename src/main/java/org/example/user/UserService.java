package org.example.user;

import org.example.logger.AppLogger;
import org.springframework.beans.factory.annotation.Autowired;
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

    public boolean isValidUser(String username){
        User user = userRestRepository.findByuserName(username);
        if(user == null){
            System.out.println("User Not Valid");
        } else {
            return true;
        }
        return false;
    }
}
