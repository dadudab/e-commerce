package com.server.ecommerce.service;

import com.server.ecommerce.entity.User;

public interface IUserService {
    User getUserById(Long id);
    void updateUser(User user);
}
