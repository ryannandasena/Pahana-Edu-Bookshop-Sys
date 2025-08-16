package com.pahanaedu.service;

import com.pahanaedu.model.Customer;
import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(String accountNumber);
}
