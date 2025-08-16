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
</head>
<body>
<h2><%= editMode ? "Edit User" : "Add New User" %></h2>
<p><a href="<%= request.getContextPath() %>/user?action=list">Back to User List</a></p>

<form method="post" action="<%= request.getContextPath() %>/user">
    <% if (editMode) { %>
        <input type="hidden" name="id" value="<%= userToEdit.getId() %>">
    <% } %>

    <label>Username:</label><br>
    <input type="text" name="username" value="<%= editMode ? userToEdit.getUsername() : "" %>" required><br><br>

    <label>Password:</label><br>
    <input type="password" name="password" value="<%= editMode ? userToEdit.getPassword() : "" %>" required><br><br>

    <label>Role:</label><br>
    <select name="role" required>
        <option value="ADMIN" <%= editMode && "ADMIN".equals(userToEdit.getRole()) ? "selected" : "" %>>ADMIN</option>
        <option value="CASHIER" <%= editMode && "CASHIER".equals(userToEdit.getRole()) ? "selected" : "" %>>CASHIER</option>
    </select><br><br>

    <button type="submit"><%= editMode ? "Update User" : "Add User" %></button>
</form>
</body>
</html>
