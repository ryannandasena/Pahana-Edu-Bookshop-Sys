<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.pahanaedu.model.User" %>
<%
    User loggedInUser = (User) session.getAttribute("user");
    if (loggedInUser == null) { response.sendRedirect(request.getContextPath() + "/login.jsp"); return; }

    User userToEdit = (User) request.getAttribute("userToEdit");
    boolean editMode = (userToEdit != null);
%>
<html>
<head>
    <title><%= editMode ? "Edit User" : "Add User" %></title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0fff0;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .form-card {
            background-color: #e8f5e9;
            padding: 30px 25px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            width: 100%;
            max-width: 400px;
        }
        h2 {
            color: #2e7d32;
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
            margin-top: 10px;
        }
        input[type="text"], input[type="password"], select {
            width: 100%;
            padding: 8px 10px;
            margin-bottom: 15px;
            border: 1px solid #a5d6a7;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 14px;
        }
        input[type="text"]:focus, input[type="password"]:focus, select:focus {
            border-color: #2e7d32;
            outline: none;
            box-shadow: 0 0 5px rgba(46,125,50,0.5);
        }
        button {
            width: 100%;
            background-color: #2e7d32;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 15px;
            margin-top: 10px;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #1b5e20;
        }
        .top-links {
            text-align: center;
            margin-bottom: 15px;
        }
        .top-links a {
            color: #2e7d32;
            text-decoration: none;
            font-weight: bold;
        }
        .top-links a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="form-card">
    <h2><%= editMode ? "Edit User" : "Add New User" %></h2>
    <p class="top-links"><a href="<%= request.getContextPath() %>/user?action=list">Back to User List</a></p>

    <form method="post" action="<%= request.getContextPath() %>/user">
        <% if (editMode) { %>
            <input type="hidden" name="id" value="<%= userToEdit.getId() %>">
        <% } %>

        <label>Username:</label>
        <input type="text" name="username" value="<%= editMode ? userToEdit.getUsername() : "" %>" required>

        <label>Password:</label>
        <input type="password" name="password" value="<%= editMode ? userToEdit.getPassword() : "" %>" required>

        <label>Role:</label>
        <select name="role" required>
            <option value="ADMIN" <%= editMode && "ADMIN".equals(userToEdit.getRole()) ? "selected" : "" %>>ADMIN</option>
            <option value="CASHIER" <%= editMode && "CASHIER".equals(userToEdit.getRole()) ? "selected" : "" %>>CASHIER</option>
        </select>

        <button type="submit"><%= editMode ? "Update User" : "Add User" %></button>
    </form>
</div>
</body>
</html>
