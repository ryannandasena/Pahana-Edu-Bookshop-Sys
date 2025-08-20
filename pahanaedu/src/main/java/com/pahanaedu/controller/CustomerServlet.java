package com.pahanaedu.controller;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.dao.impl.CustomerDAOImpl;
import com.pahanaedu.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {

    private CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                request.getRequestDispatcher("/WEB-INF/views/customer/add-customer.jsp").forward(request, response);
                break;

            case "edit":
                String id = request.getParameter("id");
                Customer existingCustomer = customerDAO.getCustomerById(id);
                request.setAttribute("customer", existingCustomer);
                request.getRequestDispatcher("/WEB-INF/views/customer/edit-customer.jsp").forward(request, response);
                break;

            case "delete":
                String deleteId = request.getParameter("id");
                customerDAO.deleteCustomer(deleteId);
                response.sendRedirect(request.getContextPath() + "/customer?action=list");
                break;

            default: // list
                List<Customer> list = customerDAO.getAllCustomers();
                request.setAttribute("customers", list);
                request.getRequestDispatcher("/WEB-INF/views/customer/customers.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String action = request.getParameter("action");

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        int unitsConsumed = Integer.parseInt(request.getParameter("units_consumed"));

        if ("insert".equals(action)) {
            // Pass null for account number — MySQL will auto-generate
            Customer newCustomer = new Customer(null, name, address, telephone, unitsConsumed);
            customerDAO.addCustomer(newCustomer);
        } else if ("update".equals(action)) {
            String accountNumber = request.getParameter("account_number");
            Customer updatedCustomer = new Customer(accountNumber, name, address, telephone, unitsConsumed);
            customerDAO.updateCustomer(updatedCustomer);
        }

        response.sendRedirect(request.getContextPath() + "/customer?action=list");
    }
}
