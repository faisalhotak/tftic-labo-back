package be.portal.job.utils;

import be.portal.job.configs.JwtConfig;
import be.portal.job.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
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
                .claim("id", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpireAt()))
                .compact();
    }

    public Claims getClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public Long getId(String token) {
        return getClaims(token).get("id", Long.class);
    }

    public boolean validateToken(String token) {
        Claims claims = getClaims(token);
        Date now = new Date();

        return now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }
}
