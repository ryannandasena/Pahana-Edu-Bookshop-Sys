package com.pahanaedu.servlet;

import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        User loggedInUser = (User) session.getAttribute("user");

        // Only ADMIN can access
        if (!"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/no-access.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                request.getRequestDispatcher("/WEB-INF/views/user/user-form.jsp").forward(request, response);
                break;

            case "edit":
                int editId = Integer.parseInt(request.getParameter("id"));
                User userToEdit = userService.getUserById(editId);
                request.setAttribute("userToEdit", userToEdit);
                request.getRequestDispatcher("/WEB-INF/views/user/user-form.jsp").forward(request, response);
                break;

            case "delete":
                int deleteId = Integer.parseInt(request.getParameter("id"));
                userService.deleteUser(deleteId);
                response.sendRedirect(request.getContextPath() + "/user?action=list");
                break;

            case "list":
            default:
                List<User> users = userService.getAllUsers();
                request.setAttribute("users", users);
                request.getRequestDispatcher("/WEB-INF/views/user/user-list.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        User loggedInUser = (User) session.getAttribute("user");

        // Only ADMIN can access
        if (!"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/no-access.jsp");
            return;
        }

        String idParam = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User user;
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            user = new User(id, username, password, role);
            userService.updateUser(user);
        } else {
            user = new User(0, username, password, role); // id=0 for new user, DAO will handle auto_increment
            userService.addUser(user);
        }

        response.sendRedirect(request.getContextPath() + "/user?action=list");
    }
}
