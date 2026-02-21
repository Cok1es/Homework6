package service;

import dao.UserDAO;
import model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Подключает Mockito к JUnit 5
class UserServiceTest {

    @Mock
    private UserDAO userDAO; // Создаем "куклу" (заглушку) для DAO

    @InjectMocks
    private UserService userService; // Сюда Mockito само вставит наш мок выше

    @Test
    void shouldFailWhenUserIsUnderage() {
        // Создаем пользователя, который не пройдет валидацию
        User youngUser = new User("Kid", "kid@test.com", 10);

        // Проверяем, что выкидывается ошибка
        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(youngUser);
        });

        // Проверяем, что DAO метод save НИ РАЗУ не вызывался
        verify(userDAO, never()).save(any());
    }

    @Test
    void shouldRegisterAdultUser() {
        User adultUser = new User("Adult", "adult@test.com", 20);

        userService.registerUser(adultUser);

        // Проверяем, что сервис действительно вызвал метод save у DAO
        verify(userDAO, times(1)).save(adultUser);
    }
}
