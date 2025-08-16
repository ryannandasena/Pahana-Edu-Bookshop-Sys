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
</head>
<body>
<h2><%= isEdit ? "Edit Item" : "Add Item" %></h2>

<form action="<%= request.getContextPath() %>/item" method="post">
    <input type="hidden" name="action" value="<%= isEdit ? "update" : "insert" %>">
    <% if (isEdit) { %>
        <input type="hidden" name="item_id" value="<%= item.getItemId() %>">
    <% } %>

    Name: <input type="text" name="item_name" value="<%= isEdit ? item.getItemName() : "" %>" required><br><br>
    Price: <input type="number" step="0.01" name="price" value="<%= isEdit ? item.getPrice() : "" %>" required><br><br>
    Units in Stock: <input type="number" name="stock_quantity" value="<%= isEdit ? item.getUnitsInStock() : 0 %>" required><br><br>
    <input type="submit" value="<%= isEdit ? "Update" : "Add" %>">
</form>

<a href="<%= request.getContextPath() %>/item?action=list">Back to List</a>
</body>
</html>
