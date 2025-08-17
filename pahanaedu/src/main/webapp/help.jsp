<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.model.User" %>
<%
    // Ensure user is logged in
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    User user = (User) session.getAttribute("user");
%>
<html>
<head>
    <title>Help Guidelines - Pahana Edu</title>
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
            max-width: 800px;
            margin: 30px auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #2d6a4f;
            margin-bottom: 15px;
        }
        ol {
            color: #333;
            line-height: 1.8;
        }
        a.back-link {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #2d6a4f;
            font-weight: bold;
            padding: 8px 12px;
            border: 1px solid #2d6a4f;
            border-radius: 4px;
            transition: all 0.3s;
        }
        a.back-link:hover {
            background-color: #2d6a4f;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Help & Guidelines</h1>
    </div>

    <div class="container">
        <h2>System Usage Guidelines</h2>
        <ol>
            <li>Login using your username and password.</li>
            <li>Use the Dashboard to navigate between Customers, Items, Bills, and Users (Admins only).</li>
            <li>To create a new record, click the relevant <strong>“Add”</strong> button on each page.</li>
            <li>To edit or delete records, use the action buttons provided in the tables.</li>
            <li>You can view and print bills from the <strong>Bills</strong> section.</li>
            <li>Always logout after using the system to keep your account secure.</li>
        </ol>

        <a href="<%= request.getContextPath() %>/dashboard">Dashboard</a> 
    </div>
</body>
</html>
