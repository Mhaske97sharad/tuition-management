package com.tuition.service;

import java.util.Date;

public interface JwtService {

    String generateToken(String email);

    String extractUsername(String token);

    Date extractExpiration(String token);

    boolean isTokenExpired(String token);

    boolean isTokenValid(String token, String email);
}