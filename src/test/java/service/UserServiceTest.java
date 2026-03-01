package service;

import model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldFailWhenUserIsUnderage() {

        User youngUser = new User("Kid", "kid@test.com", 10);


        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(youngUser);
        });


        verify(userDAO, never()).save(any());
    }

    @Test
    void shouldRegisterAdultUser() {
        User adultUser = new User("Adult", "adult@test.com", 20);

        userService.registerUser(adultUser);


        verify(userDAO, times(1)).save(adultUser);
    }
}
