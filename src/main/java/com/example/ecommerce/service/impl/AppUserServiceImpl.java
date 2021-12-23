package com.example.ecommerce.service.impl;


import com.example.ecommerce.dto.RegisterRequest;
import com.example.ecommerce.exception.MyException;
import com.example.ecommerce.model.AppUser;
import com.example.ecommerce.repository.AppUserRepository;
import com.example.ecommerce.service.AppUserService;
import com.example.ecommerce.utils.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUser findUserById( Long id) throws UsernameNotFoundException{
        AppUser user = appUserRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User Not found"));
        return user;
    }


    @Transactional
    public AppUser addUser( RegisterRequest registerRequest) {
        AppUser user = new AppUser();
        if (appUserRepository.findAppUserByEmail(registerRequest.getEmail()).isPresent()){
            throw new MyException("Email already taken");
        }
        if (ObjectUtil.isAnyFieldEmpty(registerRequest)){
            throw new MyException("Field is empty");
        }
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setRole(registerRequest.getRole());
        user.setEnabled(false);
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        /*
        if (registerRequest.getPassword().length() < 8){
            throw new MyException("Password must be at least 8 charcters");
        }
        */

        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        appUserRepository.save(user);
        return user;
    }

    @Transactional
    public AppUser updateUser( AppUser appuser) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findById(appuser.getId())
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        if (appuser.getPassword().isEmpty() || appuser.getUsername().isEmpty()
                || appuser.getEmail().isEmpty()|| appuser.getRole().isEmpty() ){
            throw new MyException("A field is empty");
        }
        user.setUsername(appuser.getUsername());
        user.setEmail(appuser.getEmail());
        user.setEnabled(appuser.isEnabled());
        user.setRole(appuser.getRole());
        user.setCreatedAt(appuser.getCreatedAt());
        user.setUpdatedAt(Instant.now());
        user.setPassword(passwordEncoder.encode(appuser.getPassword()));
        appUserRepository.save(user);
        return user;
    }

    public void deleteUser( Long id){
        AppUser user = findUserById(id);
        appUserRepository.delete(user);
    }


}
