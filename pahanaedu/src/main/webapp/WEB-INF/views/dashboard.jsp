<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.model.User" %>
<%
    // Check if user is logged in
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    User user = (User) session.getAttribute("user");
    String role = user.getRole();
%>
<html>
<head>
    <title>Pahana Edu - Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= user.getUsername() %>!</h2>
    <p>You are logged in as <strong><%= role %></strong></p>

    <!-- Navigation Menu -->
    <ul>
        <!-- Both Admin and Cashier/User can manage Customers -->
        <li><a href="<%= request.getContextPath() %>/customer?action=list">Manage Customers</a></li>

        <!-- Both Admin and Cashier/User can manage Items -->
        <li><a href="<%= request.getContextPath() %>/item?action=list">Manage Items</a></li>

        <!-- Both Admin and Cashier/User can create/view Bills -->
        <li><a href="<%= request.getContextPath() %>/bill?action=list">View Bills</a></li>
        <li><a href="<%= request.getContextPath() %>/bill?action=new">Create Bill</a></li>

        <!-- Only Admin can manage Users -->
        <% if ("ADMIN".equalsIgnoreCase(role)) { %>
            <li><a href="<%= request.getContextPath() %>/user?action=list">Manage Users</a></li>
        <% } %>

        <li><a href="<%= request.getContextPath() %>/logout.jsp">Logout</a></li>
    </ul>
</body>
</html>
