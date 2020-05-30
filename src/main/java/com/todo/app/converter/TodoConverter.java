package com.todo.app.converter;

import com.todo.app.entity.Todo;
import com.todo.app.entity.dto.TodoDto;
import com.todo.app.entity.dto.TodoRequest;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TodoConverter {

    public static TodoDto toDto(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .dueDate(todo.getDueDate())
                .done(todo.getDone())
                .createdAt(todo.getCreatedAt())
                .build();
    }

    public static List<TodoDto> toDto(List<Todo> todos) {
        return todos.stream()
                .map(TodoConverter::toDto)
                .collect(Collectors.toList());
    }

    public static Todo toEntity(TodoRequest request) {
        return Todo.builder()
                .id(UUID.randomUUID())
                .title(request.getTitle())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .createdAt(new Date())
                .done(false)
                .build();

    }
}
