package com.example.service;

import com.example.dto.UserDTO;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO create(UserDTO dto) {
        User user = new User(dto.name(), dto.email(), dto.age());
        User saved = userRepository.save(user);
        return new UserDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.getAge());
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(u -> new UserDTO(u.getId(), u.getName(), u.getEmail(), u.getAge()))
                .collect(Collectors.toList());
    }

    // Обновление

    public UserDTO update(Long id, UserDTO dto) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден с id: " + id));


        existingUser.setName(dto.name());
        existingUser.setEmail(dto.email());
        existingUser.setAge(dto.age());


        User updatedUser = userRepository.save(existingUser);


        return new UserDTO(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(), updatedUser.getAge());
    }

    // Удаление

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Нельзя удалить: пользователь не найден");
        }
        userRepository.deleteById(id);
    }

}
