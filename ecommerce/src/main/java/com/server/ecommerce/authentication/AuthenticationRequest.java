package com.server.ecommerce.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationRequest {
    private String email;
    private String password;
}
