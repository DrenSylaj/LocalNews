package com.localnews.user.config;

import com.localnews.user.entities.User;
import com.localnews.user.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtService {
//    @Value("${application.config.secret-key}")
    private String SECRET_KEY = "598CCC87F435776673862D86F8B93B2B67A0C2CEA75F91CF89A285D971E7C12F";
    private final UserRepository userRepository;

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());


    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }



    public Optional<User> findUserByJwt(String jwt){
        String email = getEmailFromJwt(jwt);
        System.out.println("Username: "+email);

        return userRepository.findByEmail(email);
    }

    public String generateToken(Authentication authentication){
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roles = populateAuthorities(authorities);

        return Jwts
                .builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 24))
                .claim("email", authentication.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    public String getEmailFromJwt(String jwt){
        Claims claims = null;
        System.out.println("jwt before: "+jwt);

        String token = jwt.startsWith("Bearer ") ? jwt.substring(7) : jwt;

        System.out.println("Jwt after: "+token);

        try{
            claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        }
        catch (JwtException e){
            e.printStackTrace();
        }

        assert claims != null;
        return String.valueOf(claims.get("email"));
    }

    private Claims extractAllClaims(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities){
        Set<String> auth = new HashSet<>();

        for(GrantedAuthority authority : authorities){
            auth.add(authority.getAuthority());
        }

        return String.join(",", auth);
    }




}
