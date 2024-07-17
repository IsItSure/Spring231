package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    User getUserById(int id);
    List<User> getUsers();
}
