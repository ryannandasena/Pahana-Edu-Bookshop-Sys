package com.pahanaedu.service.impl;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.dao.impl.CustomerDAOImpl;
import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(String accountNumber) {
        return customerDAO.getCustomerById(accountNumber);
    }
}
