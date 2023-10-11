package com.example.accountservice.security.utils;

import com.example.accountservice.model.Account;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {


    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }


    public String jwtBuilder(String username, Date date, Date expiryDate) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(date)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    //function for create the token for sing
    public String createToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000); // 1 day
        return jwtBuilder(userDetails.getUsername(), new Date(), expiryDate);
    }

    // another function for create token when singup
    public String Token(Account account) {
        UserDetails userDetails = account;
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 900000); // 15 minutes
        return jwtBuilder(userDetails.getUsername(),new Date(), expiryDate);
    }


    public String refreshToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 604800000); // 7 day
        return jwtBuilder(userDetails.getUsername(),new Date(), expiryDate);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return  bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        // Check if the token is valid and not expired
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty");
        } catch (SignatureException e) {
            log.error("there is an error with the signature of you token ");
        }
        return false;
    }

    public String getUsername(String token) {
        // Extract the username from the JWT token
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
