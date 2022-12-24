package com.server.ecommerce.service;

import com.server.ecommerce.entity.UserRole;

public interface IUserRoleService {
    void createUserRole(UserRole userRole);
    UserRole getUserRoleById(Long id);
    void assignUserRoleToUser(Long userRoleId, Long userId);
    void createUserRoleAndAssignToUser(UserRole userRole, Long userId);
}
