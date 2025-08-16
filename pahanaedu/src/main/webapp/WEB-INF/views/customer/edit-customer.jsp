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
<head><title>Edit Customer</title></head>
<body>
<h2>Edit Customer</h2>
<form action="<%= request.getContextPath() %>/customer" method="post">
    <input type="hidden" name="action" value="update">
   <input type="hidden" name="account_number" value="${customer.accountNumber}">
    Name: <input type="text" name="name" value="${customer.name}" required><br>
    Address: <input type="text" name="address" value="${customer.address}" required><br>
    Telephone: <input type="text" name="telephone" value="${customer.telephone}" required><br>
    Units Consumed: <input type="number" name="units_consumed" value="${customer.unitsConsumed}" required><br>
    <input type="submit" value="Update Customer">
</form>
<a href="<%= request.getContextPath() %>/customer?action=list">Back to Customer List</a>
</body>
</html>
