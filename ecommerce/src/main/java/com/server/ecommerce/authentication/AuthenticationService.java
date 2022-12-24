package com.server.ecommerce.authentication;

import com.server.ecommerce.exception.AlreadyExistsException;
import com.server.ecommerce.exception.BadDataException;
import com.server.ecommerce.model.User;
import com.server.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public String registerUser(User user) {
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
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        userRepository.save(newUser);

        return "success";
    }

    public String authenticateUser(AuthenticationRequest authenticationRequest) {
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
            throw new BadCredentialsException("Wrong password or email");
        }
        return "AUTH SUCCESS";
    }
}
