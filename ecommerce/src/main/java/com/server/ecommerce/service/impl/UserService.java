package com.server.ecommerce.service.impl;

import com.server.ecommerce.exception.DataNotFoundException;
import com.server.ecommerce.entity.User;
import com.server.ecommerce.repository.UserRepository;
import com.server.ecommerce.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }
}
