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

<!-- Your HTML form for editing a customer -->
<html>
<head>
    <title>Edit Customer</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0fdf4;
            margin: 0;
            padding: 20px;
        }
        h2 {
            color: #166534;
            text-align: center;
        }
        form {
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            max-width: 400px;
            margin: 20px auto;
        }
        label {
            font-weight: bold;
            color: #166534;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 8px;
            margin: 6px 0 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button, input[type="submit"] {
            background-color: #16a34a;
            color: white;
            padding: 10px 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            width: 100%;
            font-size: 14px;
        }
        button:hover, input[type="submit"]:hover {
            background-color: #15803d;
        }
        a {
            display: block;
            margin-top: 20px;
            text-align: center;
            color: #16a34a;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h2>Edit Customer</h2>
    <form action="<%= request.getContextPath() %>/customer" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="account_number" value="${customer.accountNumber}">

        <label>Name:</label>
        <input type="text" name="name" value="${customer.name}" required>

        <label>Address:</label>
        <input type="text" name="address" value="${customer.address}" required>

        <label>Telephone:</label>
        <input type="text" name="telephone" value="${customer.telephone}" required>

        <label>Units Consumed:</label>
        <input type="number" name="units_consumed" value="${customer.unitsConsumed}" required>

        <input type="submit" value="Update Customer">
    </form>
    <a href="<%= request.getContextPath() %>/customer?action=list">Back to Customer List</a>
</body>
</html>
