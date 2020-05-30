package com.todo.app.entity.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class AuthRequest {
    @NotBlank
    @Length(max = 25)
    private String userName;
    @NotBlank
    @Length(max = 25)
    private String password;
}
