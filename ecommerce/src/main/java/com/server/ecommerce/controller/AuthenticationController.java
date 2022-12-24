package com.server.ecommerce.controller;

import com.server.ecommerce.authentication.AuthenticationRequest;
import com.server.ecommerce.authentication.AuthenticationService;
import com.server.ecommerce.authentication.CustomUserDetailsService;
import com.server.ecommerce.model.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/auth/register")
    public void registerUser(@RequestBody User user) {
        authenticationService.registerUser(user);
    }

    @PostMapping("/auth/login")
    public String loginUser(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.authenticateUser(authenticationRequest);
    }
}
