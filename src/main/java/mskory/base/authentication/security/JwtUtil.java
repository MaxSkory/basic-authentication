package mskory.base.authentication.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import javax.crypto.SecretKey;
import mskory.base.authentication.exception.JwtInitException;
import mskory.base.authentication.exception.JwtParsingException;
import mskory.base.authentication.exception.JwtValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private static final int NUMBER_OF_KEY_BYTES = 32;
    private final SecretKey key;
    @Value("${jwt.token.expiration-time}")
    private Long expirationTime;

    public JwtUtil() {
        try {
            key = Keys.hmacShaKeyFor(
                    SecureRandom.getInstanceStrong().generateSeed(NUMBER_OF_KEY_BYTES));
        } catch (NoSuchAlgorithmException e) {
            throw new JwtInitException("Can't generate token secret key.", e);
        }
    }

    public String generateToken(String subject) {
        ZonedDateTime currentZonedDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault());
        return Jwts.builder()
                .subject(subject)
                .issuedAt(Date.from(currentZonedDateTime.toInstant()))
                .expiration(Date.from(currentZonedDateTime.plusMinutes(expirationTime).toInstant()))
                .signWith(key)
                .compact();
    }

    public void validate(String token) {
        Claims claims = this.getClaims(token);
        if (!claims.getExpiration().after(new Date())) {
            throw new JwtValidationException("Jwt token expired");
        }
    }

    public String getSubject(String token) {
        return this.getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new JwtParsingException("Can't parse jwt token.", e);
        }
    }
}
