package com.todo.app.service;

import com.todo.app.converter.TodoConverter;
import com.todo.app.entity.Todo;
import com.todo.app.entity.dto.TodoDto;
import com.todo.app.entity.dto.TodoRequest;
import com.todo.app.exception.NotFoundException;
import com.todo.app.repository.TodoRepository;
import lombok.SneakyThrows;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final ExpirationService expirationService;

    public TodoService(TodoRepository todoRepository, ExpirationService expirationService) {
        this.todoRepository = todoRepository;
        this.expirationService = expirationService;
    }

    public List<TodoDto> getAllTodo() {
        List<Todo> todos = todoRepository.findAll();
        todos.sort(Comparator.comparing(Todo::getCreatedAt).thenComparing(Todo::getDone));
        return TodoConverter.toDto(todos);
    }

    public TodoDto getTodoById(UUID id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Todo with id %s not found.", id)));

        return TodoConverter.toDto(todo);
    }

    @Transactional
    public TodoDto createTodo(TodoRequest todoRequest) throws SchedulerException {
        Todo todo = TodoConverter.toEntity(todoRequest);
        Todo savedTodo = todoRepository.save(todo);
        expirationService.createExpirationTrigger(savedTodo);
        return TodoConverter.toDto(savedTodo);
    }

    public void deleteTodoById(UUID id) {
        todoRepository.deleteById(id);
    }

    public List<TodoDto> getAllExpiredTodo() {
        Date now = new Date();
        List<Todo> expiredTodos = todoRepository.getAllByDueDateBefore(now);
        return TodoConverter.toDto(expiredTodos);
    }
}
