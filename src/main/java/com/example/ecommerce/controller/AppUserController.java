package com.example.ecommerce.controller;

import com.example.ecommerce.dto.RegisterRequest;
import com.example.ecommerce.model.AppUser;
import com.example.ecommerce.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUser(@PathVariable("id") Long id){
        AppUser user = appUserService.findUserById(id);
        return new ResponseEntity<AppUser>(user, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<AppUser> addUser(@RequestBody RegisterRequest registerRequest){
        AppUser user = appUserService.addUser(registerRequest);
        return new ResponseEntity<AppUser>(user, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser appUser){
        AppUser user = appUserService.updateUser(appUser);
        return new ResponseEntity<AppUser>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        appUserService.deleteUser(id);
        return new ResponseEntity<String>("User deleted succesfully", HttpStatus.OK);
    }
}
