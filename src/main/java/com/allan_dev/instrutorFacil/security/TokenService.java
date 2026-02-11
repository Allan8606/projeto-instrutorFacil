package com.allan_dev.instrutorFacil.security;


import com.allan_dev.instrutorFacil.dto.JWTUserData;
import com.allan_dev.instrutorFacil.entity.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;


    public String generateToken(UserEntity user){

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(user.getLogin())
                .withClaim("userId", user.getUserId())
                .withClaim("name", user.getName())
                .withClaim("role", user.getRole().name())
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .withIssuedAt(Instant.now())
                .withIssuer("Api Instrutor Fácil")
                .sign(algorithm);
    }

    public Optional<JWTUserData> verifyToken(String token){


        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("Api Instrutor Fácil")
                    .build()
                    .verify(token);

            return Optional.of(JWTUserData
                    .builder()
                    .id(jwt.getClaim("userId").asLong())
                    .name(jwt.getClaim("name").asString())
                    .email(jwt.getSubject())
                    .role(jwt.getClaim("role").asString())
                    .build());

        }catch (JWTVerificationException e){
            return Optional.empty();
        }

    }

}
