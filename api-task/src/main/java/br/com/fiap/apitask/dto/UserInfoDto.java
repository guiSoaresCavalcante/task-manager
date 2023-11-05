package br.com.fiap.apitask.dto;

public record UserInfoDto(
        String username,
        String password,
        String roles
) {
}
