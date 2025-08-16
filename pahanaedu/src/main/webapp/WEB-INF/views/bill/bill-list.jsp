<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.pahanaedu.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) { response.sendRedirect(request.getContextPath() + "/login.jsp"); return; }
%>
<html>
<head>
    <title>Bill List</title>
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
    table {
        border-collapse: collapse;
        width: 100%;
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
    .nested-table th {
        background-color: #dcedc8;
        font-size: 13px;
    }
    .nested-table td {
        font-size: 13px;
    }
    .actions a {
        margin-right: 5px;
        padding: 3px 6px;
        border-radius: 3px;
        font-size: 12px;
        color: white;
    }
    .actions a.delete {
        background-color: #c62828; /* red */
    }
    .actions a.delete:hover {
        background-color: #8e0000;
    }
    .actions a.print {
        background-color: #1565c0; /* blue */
    }
    .actions a.print:hover {
        background-color: #0d47a1;
    }
    p, .top-links {
        margin-bottom: 10px;
    }
</style>
</head>
<body>
<h2>All Bills</h2>
<p class="top-links">
    Logged in as: <b><%= user.getUsername() %></b> |
    <a href="<%= request.getContextPath() %>/dashboard">Dashboard</a> |
    <a href="<%= request.getContextPath() %>/logout.jsp">Logout</a>
</p>

<p><a href="<%= request.getContextPath() %>/bill?action=new">+ Create Bill</a></p>

<table>
    <tr>
        <th>Bill ID</th>
        <th>Customer Acc</th>
        <th>Bill Date</th>
        <th>Items</th>
        <th>Total</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="b" items="${bills}">
        <tr>
            <td>${b.billId}</td>
            <td>${b.customerName}</td>
            <td>${b.billDate}</td>
            <td>
                <table class="nested-table">
                    <tr><th>Item</th><th>Qty</th><th>Price</th><th>Subtotal</th></tr>
                    <c:forEach var="item" items="${b.billItems}">
                        <tr>
                            <td>${item.itemName}</td>
                            <td>${item.quantity}</td>
                            <td>${item.price}</td>
                            <td>${item.quantity * item.price}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
            <td>${b.totalAmount}</td>
            <td class="actions">
    <a class="delete" href="${pageContext.request.contextPath}/bill?action=delete&id=${b.billId}" 
       onclick="return confirm('Delete this bill?');">Delete</a>
    <a class="print" href="${pageContext.request.contextPath}/print-bill?id=${b.billId}" target="_blank">
        Print
    </a>
</td>
            
        </tr>
    </c:forEach>
</table>
</body>
</html>