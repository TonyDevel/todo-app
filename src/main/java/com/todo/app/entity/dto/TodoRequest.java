package com.todo.app.entity.dto;

import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class TodoRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @FutureOrPresent(message = "DueDate should be in the future")
    private Date dueDate;
}
