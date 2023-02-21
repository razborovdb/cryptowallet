package com.development.util;

import com.development.dynamodb.models.Users;
import com.development.env.EnvironmentVariable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class JsonWebToken {
    private ObjectMapper objectMapper = new ObjectMapper();

    private String secret = EnvironmentVariable.SECRET_TOKEN;

    public Optional<String> genJsonWebToken(Users createdUsers) {

        try {
            String json = objectMapper.writeValueAsString(createdUsers);
            Map<String, Object> claims = new HashMap<>();
            claims.put("name",createdUsers.getName());
            claims.put("email",createdUsers.getEmail());
            claims.put("role",createdUsers.getRole());
            claims.put("isAdmin",createdUsers.getAdmin());
            System.out.println("--------------  createdUsers  ------------------------");
            System.out.println(createdUsers);
            System.out.println("--------------  claims  ------------------------");
            System.out.println(claims);System.out.println("--------------  secret  ------------------------");
            System.out.println(secret);
            String jwts = Jwts.builder()
                    .setClaims(claims)
                    .signWith(
                            SignatureAlgorithm.HS512,
                            secret)
                    .compact();

            return Optional.of(jwts);
        } catch (JsonProcessingException e) {
            System.out.println("--------------  error  ------------------------");
            System.out.println("Error:   " + e.getMessage());
            return Optional.empty();
        }
    }

    private Claims getAllClaimsFromToken(String token, String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public Users getUserInformationFromToken(String token) {
        Users user = new Users();
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            user.setName((String) claims.get("name"));
            user.setAdmin((Boolean) claims.get("isAdmin"));
            user.setEmail((String) claims.get("email"));
            user.setRole((String) claims.get("role"));
        } catch (Exception e) {
            return null;
        }
        return user;
    }
}
