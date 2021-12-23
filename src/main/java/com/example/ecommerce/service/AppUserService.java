package com.example.ecommerce.service;

import com.example.ecommerce.dto.RegisterRequest;
import com.example.ecommerce.model.AppUser;

public interface AppUserService {
    AppUser updateUser(AppUser appUser);
    AppUser addUser(RegisterRequest registerRequest);
    AppUser findUserById(Long id);
    void deleteUser(Long id);
}
