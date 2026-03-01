package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        Long id,

        @NotBlank(message = "Имя не может быть пустым")
        String name,

        @Email(message = "Некорректный формат Email")
        @NotBlank(message = "Email обязателен")
        String email,

        @Min(value = 18, message = "Возраст должен быть не менее 18 лет")
        Integer age
) {}
