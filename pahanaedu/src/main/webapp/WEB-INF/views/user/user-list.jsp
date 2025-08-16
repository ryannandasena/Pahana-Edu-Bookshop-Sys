<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    User loggedInUser = (User) session.getAttribute("user");
    if (loggedInUser == null) { response.sendRedirect(request.getContextPath() + "/login.jsp"); return; }
%>
<html>
<head>
    <title>User Management</title>
</head>
<body>
<h2>All Users</h2>
<p><a href="<%= request.getContextPath() %>/dashboard">Dashboard</a> | <a href="<%= request.getContextPath() %>/logout.jsp">Logout</a></p>

<p><a href="<%= request.getContextPath() %>/user?action=new">+ Add New User</a></p>

<table border="1" cellpadding="6" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.role}</td>
            <td>
                <a href="<%= request.getContextPath() %>/user?action=edit&id=${u.id}">Edit</a> |
                <a href="<%= request.getContextPath() %>/user?action=delete&id=${u.id}"
                   onclick="return confirm('Delete this user?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
