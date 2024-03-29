package com.server.ecommerce.authentication;

import com.server.ecommerce.exception.AlreadyExistsException;
import com.server.ecommerce.exception.AuthBadCredentialsException;
import com.server.ecommerce.entity.User;
import com.server.ecommerce.repository.UserRepository;
import com.server.ecommerce.service.impl.UserRoleService;
import com.server.ecommerce.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final UserRoleService userRoleService;

    public JwtResponse registerUser(User user) {
        boolean userExists = userRepository
                .existsByUsername(user.getEmail());
        if (userExists) {
            throw new AlreadyExistsException("User already exists");
        }
        User newUser = User.builder()
                .email(user.getEmail())
                .username(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(new HashSet<>())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        userRepository.save(newUser);
        UserDetails userDetails = new CustomUserDetails(newUser, new HashSet<>());
        final String token =  jwtUtils.generateToken(userDetails);
        return new JwtResponse(token);
    }

    public JwtResponse authenticateUser(AuthenticationRequest authenticationRequest) {
        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        try {
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword(),
                            userDetails.getAuthorities()
                            );
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new AuthBadCredentialsException("Wrong email or password");
        }
        final String token = jwtUtils.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
