//package org.example.jwt;
//
//import java.security.SecureRandom;
//import java.util.Base64;
//
//public class JwtSecretKeyGenerator {
//
//    // Generate a secure JWT secret key
//    public static String generateJwtSecretKey() {
//        // Define the desired key length in bytes
//        int keyLengthBytes = 128; // 256 bits
//
//        // Generate random bytes using a SecureRandom instance
//        byte[] randomBytes = new byte[keyLengthBytes];
//        SecureRandom secureRandom = new SecureRandom();
//        secureRandom.nextBytes(randomBytes);
//
//        // Encode the random bytes using Base64 encoding
//        String jwtSecretKey = Base64.getEncoder().encodeToString(randomBytes);
//
//        return jwtSecretKey;
//    }
//
//
//
//}
