package com.server.ecommerce.service.impl;

import com.server.ecommerce.exception.DataNotFoundException;
import com.server.ecommerce.entity.User;
import com.server.ecommerce.entity.UserRole;
import com.server.ecommerce.repository.UserRoleRepository;
import com.server.ecommerce.service.IUserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRoleService implements IUserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserService userService;


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

    @Override
    public void assignUserRoleToUser(Long userRoleId, Long userId) {
        UserRole userRole = getUserRoleById(userRoleId);
        User user = userService.getUserById(userId);
        user.getRoles().add(userRole);
        userService.updateUser(user);
    }

    @Override
    public void createUserRoleAndAssignToUser(UserRole userRole, Long userId) {
        User user = userService.getUserById(userId);
        UserRole newUserRole = UserRole.builder()
                .name(userRole.getName())
                .build();
        UserRole createdUserRole = userRoleRepository.save(newUserRole);
        user.getRoles().add(createdUserRole);
        userService.updateUser(user);
    }
}
