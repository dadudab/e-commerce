package com.server.ecommerce.controller;

import com.server.ecommerce.entity.UserRole;
import com.server.ecommerce.service.impl.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UserRoleController {
    private final UserRoleService userRoleService;

    @GetMapping("/user-roles/{userRoleId}")
    public UserRole getUserRoleById(@PathVariable Long userRoleId) {
        return userRoleService.getUserRoleById(userRoleId);
    }

    @PostMapping("/user-roles/new")
    public void addUserRole(@RequestBody UserRole userRole) {
        userRoleService.createUserRole(userRole);
    }
}
