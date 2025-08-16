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
            max-width: 1000px;
            margin: 30px auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #2d6a4f;
            margin-bottom: 20px;
        }
        p {
            margin-bottom: 20px;
            color: #333;
        }
        p a {
            margin-left: 10px;
            color: #2d6a4f;
            font-weight: bold;
            text-decoration: none;
        }
        p a:hover {
            text-decoration: underline;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table th, table td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        table th {
            background-color: #2d6a4f;
            color: white;
        }
        table tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .btn {
            display: inline-block;
            padding: 6px 12px;
            font-size: 14px;
            border-radius: 4px;
            text-decoration: none;
            transition: all 0.3s;
        }
        .btn-edit {
            background-color: #40916c;
            color: white;
        }
        .btn-edit:hover {
            background-color: #2d6a4f;
        }
        .btn-delete {
            background-color: #d00000;
            color: white;
        }
        .btn-delete:hover {
            background-color: #a00000;
        }
        .btn-add {
            background-color: #2d6a4f;
            color: white;
            padding: 10px 16px;
            font-weight: bold;
        }
        .btn-add:hover {
            background-color: #1b4332;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Pahana Edu - Customer Management</h1>
    </div>

    <div class="container">
        <h2>Customer List</h2>
        <p>
            Logged in as: <strong><%= user.getUsername() %></strong> 
            | <a href="<%= request.getContextPath() %>/dashboard">Dashboard</a> 
            | <a href="<%= request.getContextPath() %>/logout.jsp">Logout</a>
        </p>

        <!-- Table of customers -->
        <table>
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
                        <a class="btn btn-edit" href="<%= request.getContextPath() %>/customer?action=edit&id=${cust.accountNumber}">Edit</a>
                        <a class="btn btn-delete" href="<%= request.getContextPath() %>/customer?action=delete&id=${cust.accountNumber}" 
                           onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <a class="btn btn-add" href="<%= request.getContextPath() %>/customer?action=new">+ Add New Customer</a>
    </div>
</body>
</html>