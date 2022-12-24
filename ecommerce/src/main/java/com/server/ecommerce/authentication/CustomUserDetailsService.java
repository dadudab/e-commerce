package com.server.ecommerce.authentication;

import com.server.ecommerce.exception.DataNotFoundException;
import com.server.ecommerce.entity.User;
import com.server.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

            Set<SimpleGrantedAuthority> roles = foundUser.getRoles()
                    .stream()
                    .map(userRole -> new SimpleGrantedAuthority(userRole.getName()))
                    .collect(Collectors.toSet());


        UserDetails userDetails = new CustomUserDetails(foundUser, roles);
        return userDetails;
    }
}
