package ar.edu.itba.bd2.redmond.auth;

import ar.edu.itba.bd2.redmond.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
    private static final long REFRESH_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;
    private static final long EXPIRATION_TIME = 1000 * 60 * 10;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String redmondId) {
        final Claims claims = Jwts.claims();
        claims.setSubject(redmondId);
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String generateRefreshToken(String redmondId) {
        final Claims claims = Jwts.claims();
        claims.setSubject(redmondId);
        claims.setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME));
        claims.put("refresh", true);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String getRedmondId(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isRefreshToken(String token) {
        return getClaims(token).get("refresh", Boolean.class) != null;
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
