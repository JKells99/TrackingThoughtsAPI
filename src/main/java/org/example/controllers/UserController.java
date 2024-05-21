package org.example.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.entities.LoginCreds;
import org.example.entities.Thought;
import org.example.entities.User;
import org.example.jwt.JwtResponse;
import org.example.jwt.JwtSecretKeyGenerator;
import org.example.logger.AppLogger;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs;

    @Value("${jwt.tokenSecret}")
    private String jwtTokenSercet;

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
        System.out.println(jwtTokenSercet);

        if(userService.loginUser(loginCreds.getUsername(),loginCreds.getPassword())){
            String username = loginCreds.getUsername();
            String token = generateJwtToken(username);
            return  ResponseEntity.ok(new JwtResponse(token));
        } else{
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username Or Password Is Incorrect/User Not Found");
        }



    }

    private String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .claim("username",username)
                .signWith(SignatureAlgorithm.HS512, jwtTokenSercet)
                .compact();
    }


}
