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
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0fff0;
            color: #333;
            margin: 20px;
        }
        h2 {
            color: #2e7d32;
        }
        a {
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        .top-links {
            margin-bottom: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            border: 1px solid #a5d6a7;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #c8e6c9;
        }
        tr:nth-child(even) {
            background-color: #e8f5e9;
        }
        .actions a {
            padding: 4px 8px;
            border-radius: 4px;
            color: white;
            font-size: 13px;
        }
        .actions a.edit {
            background-color: #1565c0; /* blue */
        }
        .actions a.edit:hover {
            background-color: #0d47a1;
        }
        .actions a.delete {
            background-color: #c62828; /* red */
        }
        .actions a.delete:hover {
            background-color: #8e0000;
        }
        .add-user {
            display: inline-block;
            background-color: #2e7d32;
            color: white;
            padding: 6px 12px;
            border-radius: 4px;
            margin-bottom: 10px;
            text-decoration: none;
        }
        .add-user:hover {
            background-color: #1b5e20;
        }
    </style>
</head>
<body>
<h2>All Users</h2>
<p class="top-links">
    <a href="<%= request.getContextPath() %>/dashboard">Dashboard</a> | 
    <a href="<%= request.getContextPath() %>/logout.jsp">Logout</a>
</p>

<p><a class="add-user" href="<%= request.getContextPath() %>/user?action=new">+ Add New User</a></p>

<table>
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
            <td class="actions">
                <a class="edit" href="<%= request.getContextPath() %>/user?action=edit&id=${u.id}">Edit</a>
                <a class="delete" href="<%= request.getContextPath() %>/user?action=delete&id=${u.id}"
                   onclick="return confirm('Delete this user?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
