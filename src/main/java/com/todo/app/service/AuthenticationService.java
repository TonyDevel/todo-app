package com.todo.app.service;

import com.todo.app.entity.Customer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(CustomerService customerService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String userName, String password) {
        return customerService.findByUserName(userName)
                .filter(c -> passwordEncoder.matches(password, c.getPassword()))
                .map(Customer::getToken)
                .orElse("");
    }

    public Customer signUp(String userName, String password) {
        return customerService.createUser(userName, password);
    }
}

