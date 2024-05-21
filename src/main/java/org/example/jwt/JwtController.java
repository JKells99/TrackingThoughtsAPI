package org.example.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.example.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class JwtController {

    @Value("${jwt.tokenSecret}")
    private String jwtTokenSecret;

    @Autowired
    private UserService userService;

    @PostMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestBody String token) {
        try {
            // Logging for debugging
            System.out.println("JWT Token Secret: " + jwtTokenSecret);
            System.out.println("JWT Token: " + token.trim());

            // Parse the claims from the JWT token using the provided secret key
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtTokenSecret.trim().getBytes())
                    .build()
                    .parseClaimsJws(token.strip())
                    .getBody();

            // Extract the username from the token claims
            String username = claims.getSubject();
            System.out.println("Username from Token: " + username);

            // Check if the user is valid
            if (!userService.isValidUser(username)) {
                // Return a 401 Unauthorized response with a message indicating the token is invalid
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }

            // Return a 200 OK response with a message indicating the token is valid
            return ResponseEntity.ok("Token is valid");
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            e.getCause();

            // Return a 401 Unauthorized response with a message indicating the token is invalid
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}
