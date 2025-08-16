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
</head>
<body>
<h2>Item List</h2>
<p>Logged in as: <%= user.getUsername() %> | <a href="<%= request.getContextPath() %>/dashboard">Dashboard</a> | <a href="<%= request.getContextPath() %>/logout.jsp">Logout</a></p>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>UnitsInStock</th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>${item.itemId}</td>
            <td>${item.itemName}</td>
            <td>${item.price}</td>
            <td>${item.unitsInStock}</td>
            <td>
                <a href="<%= request.getContextPath() %>/item?action=edit&id=${item.itemId}">Edit</a>
                <a href="<%= request.getContextPath() %>/item?action=delete&id=${item.itemId}" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="<%= request.getContextPath() %>/item?action=new">Add New Item</a>
</body>
</html>
