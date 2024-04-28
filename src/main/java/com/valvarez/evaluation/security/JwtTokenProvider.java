package com.valvarez.evaluation.security;

import com.valvarez.evaluation.exception.BlogApiException;
import com.valvarez.evaluation.payload.dto.in.UserDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Supplier;

@Component
public class JwtTokenProvider {
    @Value("${app.jwtSecret}")
    private  String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private  Long jwtExpirationDate;

    public String generateToken(UserDto user) {
        String username =  user.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key.get())
                .compact();
    }

    private final Supplier<Key> key = () -> Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.jwtSecret));

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key.get())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key.get())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "JWT Claims is empty");
        }
    }
}
