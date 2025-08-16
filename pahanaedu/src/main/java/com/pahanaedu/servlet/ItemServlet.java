package com.pahanaedu.servlet;

import com.pahanaedu.model.Item;
import com.pahanaedu.service.ItemService;
import com.pahanaedu.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/item")
public class ItemServlet extends HttpServlet {

    private ItemService itemService = new ItemServiceImpl();

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
                request.getRequestDispatcher("/WEB-INF/views/item/item-form.jsp").forward(request, response);
                break;

            case "edit":
                int editId = Integer.parseInt(request.getParameter("id"));
                Item existingItem = itemService.getItemById(editId);
                request.setAttribute("item", existingItem);
                request.getRequestDispatcher("/WEB-INF/views/item/item-form.jsp").forward(request, response);
                break;

            case "delete":
                int deleteId = Integer.parseInt(request.getParameter("id"));
                itemService.deleteItem(deleteId);
                response.sendRedirect(request.getContextPath() + "/item?action=list");
                break;

            case "list":
            default:
                List<Item> list = itemService.getAllItems();
                request.setAttribute("items", list);
                request.getRequestDispatcher("/WEB-INF/views/item/item-list.jsp").forward(request, response);
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
        String itemName = request.getParameter("item_name");
        double price = Double.parseDouble(request.getParameter("price"));
        int unitsInStock = Integer.parseInt(request.getParameter("stock_quantity"));

        if ("insert".equals(action)) {
            Item newItem = new Item(0, itemName, price ,unitsInStock); // 0 will be ignored by AUTO_INCREMENT
            itemService.addItem(newItem);
        } else if ("update".equals(action)) {
            int itemId = Integer.parseInt(request.getParameter("item_id"));
            Item updatedItem = new Item(itemId, itemName, price ,unitsInStock);
            itemService.updateItem(updatedItem);
        }

        response.sendRedirect(request.getContextPath() + "/item?action=list");
    }
}
