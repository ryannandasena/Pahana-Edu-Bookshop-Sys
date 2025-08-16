<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.pahanaedu.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Item List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0fdf4;
            margin: 0;
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #166534;
        }
        p {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        a {
            text-decoration: none;
            color: #16a34a;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background: #fff;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        th, td {
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #16a34a;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9fafb;
        }
        tr:hover {
            background-color: #dcfce7;
        }
        .actions a {
            margin: 0 5px;
            padding: 6px 10px;
            border-radius: 4px;
            font-size: 13px;
        }
        .actions a:first-child {
            background-color: #2563eb;
            color: white;
        }
        .actions a:first-child:hover {
            background-color: #1d4ed8;
        }
        .actions a:last-child {
            background-color: #dc2626;
            color: white;
        }
        .actions a:last-child:hover {
            background-color: #b91c1c;
        }
        .add-btn {
            display: block;
            width: fit-content;
            margin: 20px auto;
            background-color: #16a34a;
            color: white;
            padding: 10px 16px;
            border-radius: 6px;
            text-align: center;
        }
        .add-btn:hover {
            background-color: #15803d;
        }
    </style>
</head>
<body>
    <h2>Item List</h2>
    <p>
        Logged in as: <%= user.getUsername() %> | 
        <a href="<%= request.getContextPath() %>/dashboard">Dashboard</a> | 
        <a href="<%= request.getContextPath() %>/logout.jsp">Logout</a>
    </p>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Units In Stock</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>${item.itemId}</td>
                <td>${item.itemName}</td>
                <td>${item.price}</td>
                <td>${item.unitsInStock}</td>
                <td class="actions">
                    <a href="<%= request.getContextPath() %>/item?action=edit&id=${item.itemId}">Edit</a>
                    <a href="<%= request.getContextPath() %>/item?action=delete&id=${item.itemId}" onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a class="add-btn" href="<%= request.getContextPath() %>/item?action=new">➕ Add New Item</a>
</body>
</html>