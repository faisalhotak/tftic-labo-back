package be.portal.job.utils;

import be.portal.job.configs.JwtConfig;
import be.portal.job.entities.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private final JwtConfig jwtConfig;
    private final JwtBuilder jwtBuilder;
    private final JwtParser jwtParser;

    public JwtUtils(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.jwtBuilder = Jwts.builder().signWith(jwtConfig.getSecretKey());
        this.jwtParser = Jwts.parserBuilder().setSigningKey(jwtConfig.getSecretKey()).build();
    }

    public String generateToken(User user) {
        return jwtBuilder
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpireAt()))
                .compact();
    }

    public Claims getClaims(String token) {
        try {
            return jwtParser.parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            throw new JwtException("Invalid token");
        }
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        Claims claims;

        try {
            claims = getClaims(token);
        } catch (JwtException e) {
            return false;
        }

        Date now = new Date();

        return now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }
}
