package br.com.fiap.apitask.controller;

import br.com.fiap.apitask.dto.AuthRequest;
import br.com.fiap.apitask.dto.UserCreatedDto;
import br.com.fiap.apitask.dto.UserInfoDto;
import br.com.fiap.apitask.model.UserInfo;
import br.com.fiap.apitask.service.JwtService;
import br.com.fiap.apitask.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class UserController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity signUp (@RequestBody UserInfoDto data, UriComponentsBuilder uriBuilder) {
        UserInfo user = new UserInfo(data);
        userInfoService.addUser(user);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserCreatedDto(user));
    }

    @PostMapping("/signin")
    public String authenticateAndGetToken (@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.username());
        } else {
            throw new UsernameNotFoundException("Error");
        }
    }

    @GetMapping
    @RequestMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page<UserCreatedDto>> listAllUsers (Pageable pageable) {
        return ResponseEntity.ok(userInfoService.listAll(pageable));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Você não está em um ambiente seguro";
    }

    @GetMapping
    @RequestMapping("/teste")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String teste () {
        return "Página de usuário";
    }


}
