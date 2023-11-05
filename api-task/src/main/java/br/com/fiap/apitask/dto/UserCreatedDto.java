package br.com.fiap.apitask.dto;

import br.com.fiap.apitask.model.UserInfo;

public record UserCreatedDto (Long id, String username, String roles){

    public UserCreatedDto (UserInfo user) {
        this (user.getId(), user.getUsername(), user.getRoles());
    }
}
