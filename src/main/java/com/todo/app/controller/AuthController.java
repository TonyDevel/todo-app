package com.todo.app.controller;

import com.todo.app.entity.Customer;
import com.todo.app.entity.dto.AuthRequest;
import com.todo.app.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationService authService;

    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("signin")
    public String login(@RequestBody @Valid AuthRequest authRequest) {
        return authService.login(authRequest.getUserName(), authRequest.getPassword());
    }

    @PostMapping("signup")
    public Customer signUp(@RequestBody @Valid AuthRequest authRequest) {
        return authService.signUp(authRequest.getUserName(), authRequest.getPassword());
    }
}
