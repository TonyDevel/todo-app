package com.todo.app.repository;

import com.todo.app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUserName(String userName);

    Optional<Customer> findByToken(String token);

}
