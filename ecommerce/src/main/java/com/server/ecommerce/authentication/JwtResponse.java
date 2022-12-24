package com.server.ecommerce.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

@Builder
@AllArgsConstructor
public class JwtResponse {
    private String token;
}
