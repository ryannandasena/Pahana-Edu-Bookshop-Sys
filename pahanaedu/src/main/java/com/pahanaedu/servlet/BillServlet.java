package com.pahanaedu.servlet;

import com.pahanaedu.model.*;
import com.pahanaedu.service.BillService;
import com.pahanaedu.service.CustomerService;
import com.pahanaedu.service.ItemService;
import com.pahanaedu.service.impl.BillServiceImpl;
import com.pahanaedu.service.impl.CustomerServiceImpl;
import com.pahanaedu.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/bill")
public class BillServlet extends HttpServlet {

    private final BillService billService = new BillServiceImpl();
    private final CustomerService customerService = new CustomerServiceImpl();
    private final ItemService itemService = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        if (s == null || s.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new": {
                List<Customer> customers = customerService.getAllCustomers(); // must contain unitsConsumed
                List<Item> items = itemService.getAllItems();                 // itemId, itemName, price
                request.setAttribute("customers", customers);
                request.setAttribute("items", items);
                request.getRequestDispatcher("/WEB-INF/views/bill/add-bill.jsp").forward(request, response);
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                billService.deleteBill(id);
                response.sendRedirect(request.getContextPath() + "/bill?action=list");
                break;
            }
            case "list":
            default: {
            	List<Bill> bills = billService.getAllBills();
            	for (Bill b : bills) {
            	    Customer c = customerService.getCustomerById(String.valueOf(b.getCustomerAccountNumber()));
            	    b.setCustomerName(c != null ? c.getName() : "Unknown");

            	    List<BillItem> itemsList = billService.getItemsByBillId(b.getBillId());
            	    b.setBillItems(itemsList);

            	    double total = 0;
            	    for (BillItem bi : itemsList) {
            	        total += bi.getPrice() * bi.getQuantity();
            	    }
            	    b.setTotalAmount(total);
            	}
            	request.setAttribute("bills", bills);
            	request.getRequestDispatcher("/WEB-INF/views/bill/bill-list.jsp").forward(request, response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        if (s == null || s.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int customerAcc = Integer.parseInt(request.getParameter("customer_account_number"));
        Customer customer = customerService.getCustomerById(String.valueOf(customerAcc));
        if (customer == null) {
            response.sendRedirect(request.getContextPath() + "/bill?action=new");
            return;
        }

        String[] itemIds = request.getParameterValues("item_id[]");
        String[] qtys    = request.getParameterValues("quantity[]");

        if (itemIds == null || qtys == null || itemIds.length == 0) {
            request.setAttribute("error", "Please add at least one item.");
            doGet(request, response);
            return;
        }

        // Server-side units limit check
        int totalUnits = 0;
        for (String q : qtys) {
            try { totalUnits += Integer.parseInt(q); } catch (Exception ignored) {}
        }
        int unitsCap = customer.getUnitsConsumed(); // field in your customers table
        if (totalUnits > unitsCap) {
            request.setAttribute("error", "Total quantity (" + totalUnits + ") exceeds allowed units (" + unitsCap + ").");
            request.setAttribute("customers", customerService.getAllCustomers());
            request.setAttribute("items", itemService.getAllItems());
            request.getRequestDispatcher("/WEB-INF/views/bill/add-bill.jsp").forward(request, response);
            return;
        }

        // 1) create bill (total 0 now)
        Bill bill = new Bill();
        bill.setCustomerAccountNumber(customerAcc);
        bill.setTotalAmount(0);
        int billId = billService.addBill(bill);

        // 2) add items
        for (int i = 0; i < itemIds.length; i++) {
            int itemId = Integer.parseInt(itemIds[i]);
            int qty    = Integer.parseInt(qtys[i]);
            Item it = itemService.getItemById(itemId);
            if (it != null && qty > 0) {
                billService.addBillItem(billId, itemId, qty, it.getPrice());
            }
        }

        // 3) update total
        billService.updateBillTotal(billId);

        response.sendRedirect(request.getContextPath() + "/bill?action=list");
    }
}
