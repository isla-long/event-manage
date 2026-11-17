package com.itheima;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtTest {

    @Test 
    public void testJwt() {
        Map<String, Object> claim = new HashMap<>();
        claim.put("username", "admin");
        claim.put("role", "admin");
        String token = JWT.create()
        .withClaim("user",claim) 
        .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
        .sign(Algorithm.HMAC256("secret"));
        System.out.println(token);
    }

    // @Test
    // public void testParse(){
    //     String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"+
    //     ".eyJ1c2VybmFtZSI6eyJyb2xlIjoiYWRtaW4iLCJ1c2VybmFtZSI6ImFkbWluIn0sImV4cCI6MTc5MzE3NDY2M30"+
    //     ".0XP9sN0TOt7EApESBTP42HOUvjpsDVhfiQimieJADjc";

    //     JWTVerifier jwtVetifier = JWT.require(Algorithm.HMAC256("secret")).build();
    //     DecodedJWT decodedJWT = jwtVetifier.verify(token);
    //     System.out.println(decodedJWT.getClaims());
    //     Map<String, Claim> user = decodedJWT.getClaims();
    //     System.out.println(user);
    //     System.out.println(user.get("username"));
    // }
}
