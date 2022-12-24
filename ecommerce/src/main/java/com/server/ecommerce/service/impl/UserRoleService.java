package com.server.ecommerce.service.impl;

import com.server.ecommerce.exception.DataNotFoundException;
import com.server.ecommerce.model.UserRole;
import com.server.ecommerce.repository.UserRoleRepository;
import com.server.ecommerce.service.IUserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRoleService implements IUserRoleService {
    private final UserRoleRepository userRoleRepository;


    @Override
    public void createUserRole(UserRole userRole) {
        UserRole newUserRole = UserRole.builder()
                .name(userRole.getName())
                .build();
        userRoleRepository.save(newUserRole);
    }

    public UserRole getUserRoleById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User role not found"));
    }
}
