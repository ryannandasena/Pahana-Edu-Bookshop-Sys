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
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4fdf6;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #2d6a4f;
            color: #fff;
            padding: 15px;
            text-align: center;
        }
        .container {
            max-width: 900px;
            margin: 30px auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #2d6a4f;
            margin-bottom: 10px;
        }
        p {
            color: #333;
            margin-bottom: 20px;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        ul li {
            margin: 10px 0;
        }
        ul li a {
            display: inline-block;
            text-decoration: none;
            color: #2d6a4f;
            font-weight: bold;
            padding: 10px 15px;
            border: 1px solid #2d6a4f;
            border-radius: 4px;
            transition: all 0.3s;
        }
        ul li a:hover {
            background-color: #2d6a4f;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Pahana Edu Dashboard</h1>
    </div>

    <div class="container">
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
    </div>
</body>
</html>