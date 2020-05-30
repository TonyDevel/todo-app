package com.todo.app.controller;

import com.todo.app.entity.dto.TodoDto;
import com.todo.app.entity.dto.TodoRequest;
import com.todo.app.service.TodoService;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("time")
    public Date getTime() {
        return new Date();
    }

    @GetMapping
    public List<TodoDto> getAllTodo() {
        return todoService.getAllTodo();
    }

    @GetMapping("{id}")
    public TodoDto getTodoById(@PathVariable("id") String id) {
        UUID uuid = UUID.fromString(id);
        return todoService.getTodoById(uuid);
    }

    @PostMapping
    public TodoDto createTodo(@RequestBody @Valid TodoRequest todoDto) throws SchedulerException {
        return todoService.createTodo(todoDto);
    }

    @DeleteMapping("{id}")
    public void deleteTodoById(@PathVariable("id") String id) {
        UUID uuid = UUID.fromString(id);
        todoService.deleteTodoById(uuid);
    }

    @GetMapping("expired")
    public List<TodoDto> getAllExpiredTodo() {
        return todoService.getAllExpiredTodo();
    }
}
