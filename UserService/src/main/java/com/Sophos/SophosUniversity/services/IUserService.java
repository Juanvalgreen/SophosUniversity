package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.entities.User;

public interface IUserService {
    User getUserById(Long id) throws Exception;

    String addNewUser(User user) throws Exception;


    String verifyUser(User user) throws Exception;

    boolean validateToken(String token) throws Exception;
}
