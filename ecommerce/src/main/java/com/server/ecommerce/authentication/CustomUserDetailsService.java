package com.server.ecommerce.authentication;

import com.server.ecommerce.exception.AlreadyExistsException;
import com.server.ecommerce.exception.BadDataException;
import com.server.ecommerce.exception.DataNotFoundException;
import com.server.ecommerce.model.User;
import com.server.ecommerce.repository.UserRepository;
import com.server.ecommerce.service.impl.UserService;
import com.server.ecommerce.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        UserDetails userDetails = new CustomUserDetails(foundUser, new HashSet<>());

        return userDetails;
    }

}
