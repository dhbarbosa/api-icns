package com.br.icns.api.services.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.br.icns.api.models.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
@Transactional

public class TokenService {
    @Value("${api.secret}")
    private String SECRET;
    @Value("${api.issuer")
    private String ISSUER;
    public String gerarToken(User user) {
        return JWT.create()
                .withIssuer(this.ISSUER)
                .withSubject(user.getUsername())
                .withClaim( "id_user", user.getIdUser().toString())
                .withExpiresAt(LocalDateTime.now().plusHours(3)
                        .toInstant(ZoneOffset.of("-03:00")))
                        .sign(Algorithm.HMAC256(this.SECRET));
    }

    public String getSubject(String token){
            token = token.replace("Bearer ", "");
            return JWT.require(Algorithm.HMAC256(this.SECRET))
                    .withIssuer(this.ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();

    }
}
