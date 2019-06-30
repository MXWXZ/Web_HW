package com.imwxz.store.util.jwt;

import com.imwxz.store.entity.UserEntity;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JWTUtil {
    private static final String secretKey = "1926-08-17";

    public static String createJWT(long ttlMillis, UserEntity user) {
        long nowMillis = System.currentTimeMillis();

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("userId", user.getUserId());
        claims.put("userName", user.getUserName());
        claims.put("userPermission", user.getUserPermission());

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(nowMillis))
                .setSubject(String.valueOf(user.getUserId()))
                .signWith(SignatureAlgorithm.HS256, secretKey);
        if (ttlMillis >= 0)
            builder.setExpiration(new Date(nowMillis + ttlMillis));
        return builder.compact();
    }

    public static UserEntity parseJWT(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody();
            UserEntity ret = new UserEntity();
            ret.setUserId(Integer.valueOf(claims.get("userId").toString()));
            ret.setUserName(claims.get("userName").toString());
            ret.setUserPermission(Integer.valueOf(claims.get("userPermission").toString()));
            return ret;
        } catch (SignatureException e) {
            return null;
        }
    }
}
