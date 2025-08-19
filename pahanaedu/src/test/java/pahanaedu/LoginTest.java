package pahanaedu;
import com.pahanaedu.dao.UserDAO;

import com.pahanaedu.model.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
	 private static UserDAO userDAO;

	    @BeforeAll
	    public static void setup() {
	        userDAO = new UserDAO();
	    }

	    @Test
	    public void testValidLogin() {
	       
	        String username = "admin";
	        String password = "admin123";

	
	        User user = userDAO.login(username, password);

	       
	        assertNotNull(user, "User should not be null for valid login");
	        assertEquals(username, user.getUsername(), "Usernames should match");
	    }

	    @Test
	    public void testInvalidLogin() {
	  
	        String username = "wrongUser";
	        String password = "wrongPass";

	      
	        User user = userDAO.login(username, password);

	 
	        assertNull(user, "User should be null for invalid login");
	    }

}
