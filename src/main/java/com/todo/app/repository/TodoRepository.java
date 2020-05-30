package com.todo.app.repository;

import com.todo.app.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, UUID> {

    List<Todo> getAllByDueDateBefore(Date now);
}
