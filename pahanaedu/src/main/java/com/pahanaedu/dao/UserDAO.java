package com.pahanaedu.dao;

import com.pahanaedu.model.User;
import java.util.List;

public interface UserDAO {
    User login(String username, String password);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    List<User> getAllUsers();
    User getUserById(int id);
}