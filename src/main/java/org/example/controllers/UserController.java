package org.example.controllers;

import org.example.entities.LoginCreds;
import org.example.entities.Thought;
import org.example.entities.User;
import org.example.logger.AppLogger;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> addANewThought(@RequestBody User user){

        if(user != null){
            try{
                User newUser = userService.signUpUser(user);
                AppLogger.logSuccess("User Created Successfully");
                return ResponseEntity.ok(newUser);
            } catch (Error e){
                AppLogger.logError("Cannot Create A New User In Controller",e);
                e.printStackTrace();
                e.getCause();
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCreds loginCreds){

        if(userService.loginUser(loginCreds.getUsername(),loginCreds.getPassword())){
            return  ResponseEntity.ok("Login Good To go");
        } else{
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username Or Password Is Incorrect/User Not Found");
        }


    }


}
