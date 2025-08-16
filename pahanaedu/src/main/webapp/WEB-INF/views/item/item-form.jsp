<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.pahanaedu.model.Item" %>
<%
    Item item = (Item) request.getAttribute("item");
    boolean isEdit = item != null;
%>
<html>
<head>
    <title><%= isEdit ? "Edit Item" : "Add Item" %></title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4fff4;
            margin: 0;
            padding: 20px;
        }
        h2 {
            color: #2e7d32;
            text-align: center;
        }
        form {
            background: white;
            padding: 20px;
            max-width: 400px;
            margin: 20px auto;
            border-radius: 8px;
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
        }
        label {
            font-weight: bold;
            color: #2e7d32;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 8px;
            margin: 6px 0 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #2e7d32;
            color: white;
            border: none;
            padding: 10px 16px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }
        input[type="submit"]:hover {
            background-color: #1b5e20;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #2e7d32;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h2><%= isEdit ? "Edit Item" : "Add Item" %></h2>

<form action="<%= request.getContextPath() %>/item" method="post">
    <input type="hidden" name="action" value="<%= isEdit ? "update" : "insert" %>">
    <% if (isEdit) { %>
        <input type="hidden" name="item_id" value="<%= item.getItemId() %>">
    <% } %>

    <label>Name:</label>
    <input type="text" name="item_name" value="<%= isEdit ? item.getItemName() : "" %>" required>

    <label>Price:</label>
    <input type="number" step="0.01" name="price" value="<%= isEdit ? item.getPrice() : "" %>" required>

    <label>Units in Stock:</label>
    <input type="number" name="stock_quantity" value="<%= isEdit ? item.getUnitsInStock() : 0 %>" required>

    <input type="submit" value="<%= isEdit ? "Update" : "Add" %>">
</form>

<a href="<%= request.getContextPath() %>/item?action=list">Back to List</a>
</body>
</html>