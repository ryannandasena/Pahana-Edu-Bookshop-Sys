<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Add Customer</title>
</head>
<body>
    <h2>Add Customer</h2>
    <form action="<%= request.getContextPath() %>/customer" method="post">
        <input type="hidden" name="action" value="insert">

        <label>Name:</label><br>
        <input type="text" name="name" required><br><br>

        <label>Address:</label><br>
        <input type="text" name="address" required><br><br>

        <label>Telephone:</label><br>
        <input type="text" name="telephone" required><br><br>

        <label>Units Consumed:</label><br>
        <input type="number" name="units_consumed" required><br><br>

        <button type="submit">Save</button>
    </form>
    <br>
    <a href="<%= request.getContextPath() %>/customer?action=list">Back to List</a>
</body>
</html>
