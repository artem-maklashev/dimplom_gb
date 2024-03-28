package ru.artemmaklashev.authorization.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Сервис для работы с JSON Web Token (JWT).
 */
/**
 * Сервис для работы с JSON Web Token (JWT).
 */
@Service
public class JwtService {
    private static final String SECRET_KEY = "lE9uBMaMYqAUypYocIDo7PQbYrNkocmdYfGvZo/6dA98tbL6qO4014qAlrRvluV2";

    /**
     * Извлекает имя пользователя из токена.
     *
     * @param token Токен JWT.
     * @return Имя пользователя.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Извлекает указанный атрибут из токена.
     *
     * @param token           Токен JWT.
     * @param claimsResolver  Функция для извлечения атрибута из Claims.
     * @param <T>             Тип возвращаемого значения.
     * @return Извлеченное значение атрибута.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Генерирует токен JWT на основе данных пользователя.
     *
     * @param userDetails Данные пользователя.
     * @return Сгенерированный токен JWT.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Генерирует токен JWT на основе данных пользователя и дополнительных атрибутов.
     *
     * @param extraClaims    Дополнительные атрибуты для добавления в токен.
     * @param userDetails    Данные пользователя.
     * @return Сгенерированный токен JWT.
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        String roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .claim("roles", roles)
                .claim("approved", userDetails.isEnabled())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignKey(), Jwts.SIG.HS256)
                .compact();
    }

    /**
     * Проверяет валидность токена JWT для указанного пользователя.
     *
     * @param token         Токен JWT.
     * @param userDetails   Данные пользователя.
     * @return true, если токен валиден, иначе false.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Возвращает информацию из токена
     * @param token Токен JWT
     * @return Claims из токена
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Проверяет валидность токена и выводит информацию о подтверждении.
     *
     * @param token Токен JWT.
     */
    public void validateToken(String token) {
        Jws<Claims> jwsClaims = Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token);
        Claims claims = jwsClaims.getPayload();
        if (claims.containsKey("approved")) {
            boolean approved = claims.get("approved", Boolean.class);
            System.out.println("Approved: " + approved);
        }
    }
}
