package pahanaedu;
import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.dao.impl.CustomerDAOImpl;
import com.pahanaedu.model.Customer;
import com.pahanaedu.util.DBUtil;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOImplTest {

    private static CustomerDAO customerDAO;

    @BeforeAll
    static void setup() {
        customerDAO = new CustomerDAOImpl();
    }

    

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer();
        customer.setName("Test User");
        customer.setAddress("123 Street");
        customer.setTelephone("0771234567");
        customer.setUnitsConsumed(100);

        customerDAO.addCustomer(customer);

        // Instead of relying on accountNumber, fetch all and check one exists
        List<Customer> customers = customerDAO.getAllCustomers();
        boolean exists = customers.stream()
                .anyMatch(c -> "Test User".equals(c.getName()) && "123 Street".equals(c.getAddress()));

        assertTrue(exists, "Customer should be added successfully");
    }



    @Test
    public void testUpdateCustomer() {
        // Add a new customer
        Customer customer = new Customer();
        customer.setName("update user");
        customer.setAddress("Colombo");
        customer.setTelephone("0771234567");
        customer.setUnitsConsumed(30);
        customerDAO.addCustomer(customer);

        // Fetch all customers and find the one we just added
        List<Customer> customers = customerDAO.getAllCustomers();
        Customer saved = customers.stream()
                .filter(c -> "update user".equals(c.getName()) && "Colombo".equals(c.getAddress()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found after add"));

        // Update customer details
        saved.setName("update Updated");
        saved.setUnitsConsumed(100);
        customerDAO.updateCustomer(saved);

        // Fetch all customers again to verify the update
        List<Customer> updatedList = customerDAO.getAllCustomers();
        boolean updated = updatedList.stream()
                .anyMatch(c -> "update Updated".equals(c.getName()) && c.getUnitsConsumed() == 100);

        assertTrue(updated, "Customer should be updated successfully");
    }

    @Test
    public void testDeleteCustomer() {
        // Add a new customer
        Customer customer = new Customer();
        customer.setName("del user");
        customer.setAddress("Galle");
        customer.setTelephone("0751234567");
        customer.setUnitsConsumed(40);
        customerDAO.addCustomer(customer);

        // Fetch all customers and find the one we just added
        List<Customer> customers = customerDAO.getAllCustomers();
        Customer saved = customers.stream()
                .filter(c -> "del user".equals(c.getName()) && "Galle".equals(c.getAddress()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found after add"));

        // Delete the customer
        customerDAO.deleteCustomer(saved.getAccountNumber());

        // Verify deletion
        List<Customer> updatedList = customerDAO.getAllCustomers();
        boolean exists = updatedList.stream()
                .anyMatch(c -> "del user".equals(c.getName()) && "Galle".equals(c.getAddress()));

        assertFalse(exists, "Customer should be deleted successfully");
    }

    @Test
    public void testGetAllCustomers() {
  
        // Add multiple customers
        Customer customer1 = new Customer();
        customer1.setName("A");
        customer1.setAddress("Address1");
        customer1.setTelephone("0701111111");
        customer1.setUnitsConsumed(10);
        customerDAO.addCustomer(customer1);

        Customer customer2 = new Customer();
        customer2.setName("B");
        customer2.setAddress("Address2");
        customer2.setTelephone("0702222222");
        customer2.setUnitsConsumed(20);
        customerDAO.addCustomer(customer2);

        // Fetch all customers
        List<Customer> customers = customerDAO.getAllCustomers();

        // Verify they exist by matching their properties
        boolean hasA = customers.stream()
                .anyMatch(c -> "A".equals(c.getName()) && "Address1".equals(c.getAddress()));

        boolean hasB = customers.stream()
                .anyMatch(c -> "B".equals(c.getName()) && "Address2".equals(c.getAddress()));

        assertTrue(hasA, "Customer A should exist in the list");
        assertTrue(hasB, "Customer B should exist in the list");
        assertTrue(customers.size() >= 2, "There should be at least 2 customers");
    }

}
