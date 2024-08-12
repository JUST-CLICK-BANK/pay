package com.click.payment.utils;

import com.click.payment.domain.dto.response.PayTokenResponse;
import com.click.payment.domain.dto.response.LastStandCardResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    private final Long expiration;
    private final SecretKey paySecretKey;
    private final SecretKey userSecretKey;

    public JwtUtils(
        @Value("${jwt.paySecret}") String paySecret,
        @Value("${jwt.userSecret}") String userSecret,
        @Value("${jwt.expiration}") Long expiration
    ) {
        this.paySecretKey = Keys.hmacShaKeyFor(paySecret.getBytes());
        this.userSecretKey = Keys.hmacShaKeyFor(userSecret.getBytes());
        this.expiration = expiration;
    }

    public String generateToken(Long payId, String businessName, String failRedirUrl, String successRedirUrl, Long payAmount){
        return Jwts.builder()
            .subject(String.valueOf(payId))
            .claim("payId", payId)
            .claim("businessName", businessName)
            .claim("failRedirUrl", failRedirUrl)
            .claim("successRedirUrl", successRedirUrl)
            .claim("payAmount", payAmount)
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(paySecretKey)
            .compact();
    }

    public PayTokenResponse parsePayToken(String token) {
        Claims payload = (Claims) Jwts.parser()
            .verifyWith(paySecretKey)
            .build()
            .parse(token)
            .getPayload();

        Long payId = payload.get("payId", Long.class);
        String businessName = payload.get("businessName", String.class);
        String failRedirUrl = payload.get("failRedirUrl", String.class);
        String successRedirUrl = payload.get("successRedirUrl", String.class);
        Long payAmount = payload.get("payAmount", Long.class);

        return PayTokenResponse.from(payId, businessName, failRedirUrl, successRedirUrl, payAmount);
    }

    public UUID parseUserToken(String token) {
        Claims payload = (Claims) Jwts.parser()
            .verifyWith(userSecretKey)
            .build()
            .parse(token)
            .getPayload();

        String userId = payload.get("id", String.class);

        return UUID.fromString(userId);
    }
}
