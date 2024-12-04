package com.arrazyfathan.ecommerce.component;


import com.arrazyfathan.ecommerce.data.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("{jwt.secret}")
    private String secret;

    private SecretKey secretKey() {
        byte[] encodedKey = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(encodedKey);
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseEncryptedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        final Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String phoneNumber = extractClaim(token, Claims::getSubject);
        return phoneNumber.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String generateToken(UserEntity user) {
        final long expiration = 1000L * 60 * 60 * 24;
        return Jwts
                .builder()
                .subject(user.phoneNumber)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey())
                .compact();
    }
}
