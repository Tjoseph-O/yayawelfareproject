package com.yayawelfare.welfareproject.config.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
@Component
public class JwtService {
    public String generateAccessToken(String username){

        String token = JWT.create()
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(86400L,
                        ChronoUnit.SECONDS))
                .withIssuer("Nyaya Welfare.")
                .withSubject(username)
                .sign(Algorithm.HMAC256("secret"));
        return token;
    }

    public String extractUsernameFrom(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret"))
                .withIssuer("Nyaya Welfare")
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }
}
