package br.com.fiap.apitask.model;

import br.com.fiap.apitask.dto.UserInfoDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String roles;
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;


    public UserInfo (UserInfoDto data) {
        this.username = data.username();
        this.password = data.password();
        this.roles = data.roles();
    }

    public UserInfo() {}
}
