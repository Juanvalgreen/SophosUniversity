package com.Sophos.SophosUniversity.controllers;

import com.Sophos.SophosUniversity.dtos.UserVerifyDto;
import com.Sophos.SophosUniversity.entities.User;
import com.Sophos.SophosUniversity.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AuthController {

    @Autowired
    private IUserService service;

    @PostMapping("/api/v1/user/login")
    public UserVerifyDto login(@RequestBody User user) throws Exception {
        return service.verifyUser(user);

    }

    @GetMapping("/api/v1/user/{id}")
    public User getUserById(@PathVariable Long id) throws Exception {
        return service.getUserById(id);
    }


    @PostMapping("/api/v1/user")
    public String addNewUser(@RequestBody User user) throws Exception{
        return service.addNewUser(user);
    }


    @GetMapping("/api/v1/user/validate")
    public boolean validateToken(@RequestHeader(value = "Authorization")String token) throws Exception {
        return service.validateToken(token);
    }





}
