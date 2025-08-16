<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.pahanaedu.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) { response.sendRedirect(request.getContextPath() + "/login.jsp"); return; }
%>
<html>
<head><title>Bill List</title></head>
<body>
<h2>All Bills</h2>
<p>Logged in as: <%= user.getUsername() %> | <a href="<%= request.getContextPath() %>/dashboard">Dashboard</a> | <a href="<%= request.getContextPath() %>/logout.jsp">Logout</a></p>

<p><a href="<%= request.getContextPath() %>/bill?action=new">+ Create Bill</a></p>

<table border="1" cellpadding="6" cellspacing="0">
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
            <table border="0" cellpadding="4" cellspacing="0">
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
        <td>
    <a href="${pageContext.request.contextPath}/bill?action=delete&id=${b.billId}" 
       onclick="return confirm('Delete this bill?');">Delete</a> |
    <a href="${pageContext.request.contextPath}/print-bill?id=${b.billId}" target="_blank">
        Print
    </a>
</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
