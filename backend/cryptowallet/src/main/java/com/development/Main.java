package com.development;

import com.development.dynamodb.models.Users;
import com.development.util.JsonWebToken;

import java.util.Optional;

public class Main {
    static JsonWebToken jsonWebToken = new JsonWebToken();
    public static void main(String[] args) {
        Users createdUser = new Users();

        createdUser.setAdmin(true);
        createdUser.setEmail("1234567");
        createdUser.setName("7654321");
        Optional<String> secret = jsonWebToken.genJsonWebToken(createdUser);

        System.out.println(secret);

    }
}
