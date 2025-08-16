package com.pahanaedu.service;

import com.pahanaedu.dao.UserDAO;
import com.pahanaedu.model.User;
import java.util.List;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        if (username == null || username.isEmpty() ||
            password == null || password.isEmpty()) {
            return null;
        }
        return userDAO.login(username, password);
    }

    public void addUser(User user) { userDAO.addUser(user); }
    public void updateUser(User user) { userDAO.updateUser(user); }
    public void deleteUser(int id) { userDAO.deleteUser(id); }
    public List<User> getAllUsers() { return userDAO.getAllUsers(); }
    public User getUserById(int id) { return userDAO.getUserById(id); }
}
