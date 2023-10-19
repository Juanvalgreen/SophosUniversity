package com.Sophos.SophosUniversity.dtos;

import lombok.Data;

@Data
public class UserVerifyDto {

    private String user_mail;
    private String token;

    public UserVerifyDto(String mail, String token){
        this.user_mail = mail;
        this.token = token;
    }

}
