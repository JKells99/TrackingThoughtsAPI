package org.example.jwt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.example.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Base64;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class JwtController {

    @Value("${jwt.tokenSecret}")
    private String jwtTokenSecret;

    @Autowired
    private UserService userService;

    @PostMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestBody String requestBody) {
        try {
            // Parse the request body as JSON to extract the token
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode requestJson = objectMapper.readTree(requestBody);
            String token = requestJson.get("token").asText();

            // Logging for debugging
            System.out.println("Extracted JWT Token: " + token);

            // Clean the token
            String cleanedToken = token.replace("\"", "").trim();
            System.out.println("Cleaned JWT Token: " + cleanedToken);

            // Ensure the token is properly formatted
            if (!cleanedToken.matches("^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_.+/=]*$")) {
                System.out.println("Invalid token format");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token format");
            }

            // Decode the secret key
            byte[] secretKeyBytes = Base64.getDecoder().decode(jwtTokenSecret.trim());

            // Parse the claims from the JWT token using the provided secret key
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKeyBytes)
                    .build()
                    .parseClaimsJws(cleanedToken)
                    .getBody();

            // Extract the username from the token claims
            String username = claims.getSubject();
            System.out.println("Username from Token: " + username);

            // Check if the user is valid
            if (!userService.isValidUser(username)) {
                System.out.println("Invalid user: " + username);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }

            // Return a success response if the token is valid
            return ResponseEntity.ok("Token is valid");
        } catch (MalformedJwtException | io.jsonwebtoken.security.SecurityException e) {
            System.out.println("Invalid JWT signature");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWT signature");
        } catch (ExpiredJwtException e) {
            System.out.println("Token has expired");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token has expired");
        } catch (Exception e) {
            System.out.println("Token verification failed");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}
