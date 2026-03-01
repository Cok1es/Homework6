package com.example.controller;

import com.example.dto.UserDTO;
import com.example.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDTO createUser(@Valid @RequestBody UserDTO dto) { // Добавили @Valid
        return userService.create(dto);
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.findAll();
    }

    // Обновление

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        return userService.update(id, dto);
    }

    // Удаление

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

}
