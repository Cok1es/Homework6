package service;

import dao.UserDAO;
import model.User;
import java.util.List;

public class UserService {
    private final UserDAO userDAO;


    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void registerUser(User user) {
        if (user.getAge() < 18) {
            throw new IllegalArgumentException("Пользователь должен быть старше 18 лет");
        }
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Некорректный Email");
        }
        userDAO.save(user);
    }

    public User getUser(Long id) {
        return userDAO.findById(id);
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }
}
