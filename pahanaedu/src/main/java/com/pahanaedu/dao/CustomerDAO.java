package com.pahanaedu.dao;

import com.pahanaedu.model.Customer;
import java.util.List;

public interface CustomerDAO {
    void addCustomer(Customer customer);             // Add a new customer
    void updateCustomer(Customer customer);          // Update an existing customer
    void deleteCustomer(String accountNumber);       // Delete customer by account number
    Customer getCustomerById(String accountNumber);  // Get customer by account number
    List<Customer> getAllCustomers();                // Get list of all customers
}
