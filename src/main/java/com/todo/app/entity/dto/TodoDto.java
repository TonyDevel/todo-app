package com.todo.app.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class TodoDto {
    private UUID id;
    private String title;
    private String description;
    private Date createdAt;
    private Date dueDate;
    private Boolean done;
}
