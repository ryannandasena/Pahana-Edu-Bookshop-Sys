<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.pahanaedu.model.User" %>
<%
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    User user = (User) session.getAttribute("user");
%>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
<h2>Customer List</h2>

<p>Logged in as: <%= user.getUsername() %> | <a href="<%= request.getContextPath() %>/dashboard">Dashboard</a> | <a href="<%= request.getContextPath() %>/logout.jsp">Logout</a></p>

<!-- Table of customers here -->
<!-- Example: -->
<table border="1">
    <tr>
        <th>Account Number</th>
        <th>Name</th>
        <th>Address</th>
        <th>Telephone</th>
        <th>Units Consumed</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="cust" items="${customers}">
        <tr>
            <td>${cust.accountNumber}</td>
            <td>${cust.name}</td>
            <td>${cust.address}</td>
            <td>${cust.telephone}</td>
            <td>${cust.unitsConsumed}</td>
            <td>
                <a href="<%= request.getContextPath() %>/customer?action=edit&id=${cust.accountNumber}">Edit</a>
                <a href="<%= request.getContextPath() %>/customer?action=delete&id=${cust.accountNumber}" 
                   onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="<%= request.getContextPath() %>/customer?action=new">Add New Customer</a>
</body>
</html>
