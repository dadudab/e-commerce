package com.server.ecommerce.service;

import com.server.ecommerce.model.UserRole;

public interface IUserRoleService {
    void createUserRole(UserRole userRole);
    UserRole getUserRoleById(Long id);
}
