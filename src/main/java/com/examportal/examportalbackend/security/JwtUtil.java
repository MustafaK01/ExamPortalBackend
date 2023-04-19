package com.examportal.examportalbackend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "2646294A404E635266556A586E3272357538782F413F442A472D4B6150645367566B59703373367639792442264529482B4D6251655468576D5A713474377721";

    public String createToken(Map<String, Object> extraClaims,UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
                .signWith(getSigningKey(),SignatureAlgorithm.HS512)
                .compact();
    }

    public String generateToken(UserDetails userDetails){
        return createToken(new HashMap<>(),userDetails);
    }


    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //This method will get the userName from token
    public String getUserNameFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return !tokenExpired(token);
        }catch (SignatureException e){
            return false;
        }catch (MalformedJwtException e){
            return false;
        }catch (ExpiredJwtException e){
            return false;
        }
    }
    public Long expirationDate(String token){
        Date expirate = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expirate.getTime();
    }
    private boolean tokenExpired(String token) {
        Date expirate = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expirate.before(new Date());
    }


}