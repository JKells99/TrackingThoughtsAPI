package org.example.jwt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.example.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("api/auth")
public class JwtController {

    @Value("${jwt.tokenSecret}")
    private String jwtTokenSecret;

    @Autowired
    private UserService userService;

    @PostMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestBody String requestBody) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode requestJson = objectMapper.readTree(requestBody);
            String token = requestJson.get("token").asText();


            System.out.println("Extracted JWT Token: " + token);
            log.info("Extracted JWT Token: {}", token);


            String cleanedToken = token.replace("\"", "").trim();
            log.debug("Cleaned JWT Token: {}", cleanedToken);


            if (!cleanedToken.matches("^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_.+/=]*$")) {
                System.out.println("Invalid token format");
                log.warn("Invalid token format");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token format");
            }


            byte[] secretKeyBytes = Base64.getDecoder().decode(jwtTokenSecret.trim());


            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKeyBytes)
                    .build()
                    .parseClaimsJws(cleanedToken)
                    .getBody();


            String username = claims.getSubject();


            if (!userService.isValidUser(username)) {
                log.error("Invalid user: {}", username);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }


            return ResponseEntity.ok("Token is valid");
        } catch (MalformedJwtException | io.jsonwebtoken.security.SecurityException e) {
            log.error("Invalid JWT signature", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWT signature");
        } catch (ExpiredJwtException e) {
            log.error("Token has expired");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token has expired");
        } catch (Exception e) {
            System.out.println("Token verification failed");
            log.error("Token verification failed", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}
