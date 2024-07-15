package com.example.forohub.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.forohub.modelos.usuarios.usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String APISecret;


    public String generarToken(usuario user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(APISecret);
            return JWT.create()
                    .withIssuer("foroHub")
                    .withSubject(user.getNombre())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }



    public String getSubject(String token){
        if(token == null){
            throw new RuntimeException();
        }

        DecodedJWT verifier = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(APISecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("foroHub")
                    .build()
                    .verify(token);
            verifier.getSubject();
        }catch (JWTVerificationException exception){

        }

        if(verifier.getSubject()==null){
            throw new RuntimeException("Verifier Invalido");
        }
        return verifier.getSubject();

    }


    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}
