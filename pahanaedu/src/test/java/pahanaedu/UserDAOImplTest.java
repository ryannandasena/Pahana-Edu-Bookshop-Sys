package pahanaedu;
import com.pahanaedu.dao.UserDAO;
import com.pahanaedu.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOImplTest {

    private final UserDAO userDAO = new UserDAO();

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("pass123");
        user.setRole("admin");

        userDAO.addUser(user);

        List<User> users = userDAO.getAllUsers();
        boolean exists = users.stream()
                .anyMatch(u -> "testUser".equals(u.getUsername()) && "admin".equals(u.getRole()));

        assertTrue(exists, "User should be added successfully");
    }

   

    @Test
    public void testDeleteUser() {
        // Add a user first
        User user = new User();
        user.setUsername("del user");
        user.setPassword("1234");
        user.setRole("user");
        userDAO.addUser(user);

        // Find the inserted user
        User saved = userDAO.getAllUsers().stream()
                .filter(u -> "del user".equals(u.getUsername()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found after add"));

        // Delete user
        userDAO.deleteUser(saved.getId());

        // Verify deletion
        List<User> users = userDAO.getAllUsers();
        boolean exists = users.stream()
                .anyMatch(u -> "del user".equals(u.getUsername()));
        assertFalse(exists, "User should be deleted successfully");
    }

    @Test
    public void testGetAllUsers() {
        // Add multiple users
        User user1 = new User();
        user1.setUsername("Auser");
        user1.setPassword("pass1");
        user1.setRole("user");
        userDAO.addUser(user1);

        User user2 = new User();
        user2.setUsername("Buser");
        user2.setPassword("pass2");
        user2.setRole("admin");
        userDAO.addUser(user2);

        // Fetch all users
        List<User> users = userDAO.getAllUsers();

        boolean hasAlice = users.stream()
                .anyMatch(u -> "Auser".equals(u.getUsername()) && "user".equals(u.getRole()));
        boolean hasBob = users.stream()
                .anyMatch(u -> "Buser".equals(u.getUsername()) && "admin".equals(u.getRole()));

        assertTrue(hasAlice, "Auser should exist in the list");
        assertTrue(hasBob, "Buser should exist in the list");
        assertTrue(users.size() >= 2, "There should be at least 2 users");
    }

    
}
