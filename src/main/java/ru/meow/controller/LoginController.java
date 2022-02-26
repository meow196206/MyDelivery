package ru.meow.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.meow.dto.UserLogin;
import ru.meow.security.JwtTokenUtil;
import ru.meow.security.JwtUserDetailsService;

@RestController
@RequestMapping(path = "/login")
@AllArgsConstructor
public class LoginController {
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService jwtUserDetailsService;

    @PostMapping
    public String login(@RequestBody UserLogin userLogin) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLogin.getLogin(), userLogin.getPassword());
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(jwtUserDetailsService.loadUserByUsername(userLogin.getLogin()));
    }
}
