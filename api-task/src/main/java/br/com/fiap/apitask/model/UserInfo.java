package br.com.fiap.apitask.model;

import br.com.fiap.apitask.dto.UserInfoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String roles;


    public UserInfo (UserInfoDto data) {
        this.username = data.username();
        this.password = data.password();
        this.roles = data.roles();
    }

    public UserInfo() {}
}
