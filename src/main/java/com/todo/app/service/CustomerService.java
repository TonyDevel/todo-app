package com.todo.app.service;

import com.todo.app.entity.Customer;
import com.todo.app.repository.CustomerRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer createUser(String userName, String password) {
        Customer customer = Customer.builder()
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .token(UUID.randomUUID().toString())
                .build();
        return customerRepository.saveAndFlush(customer);
    }

    public Optional<Customer> findByUserName(String userName) {
        return customerRepository.findByUserName(userName);
    }

    public Optional<User> findByToken(String token) {
        Optional<Customer> customerOpt = customerRepository.findByToken(token);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            User user = new User(customer.getUserName(), customer.getPassword(),
                    true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
