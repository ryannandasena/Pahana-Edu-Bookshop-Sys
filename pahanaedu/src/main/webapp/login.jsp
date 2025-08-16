<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // Prevent browser caching of login page
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <title>Pahana Edu - Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4fdf6;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            background: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
            width: 320px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
            color: #2d6a4f; /* green */
        }
        label {
            float: left;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            outline: none;
            transition: border 0.3s;
        }
        input[type="text"]:focus, input[type="password"]:focus {
            border-color: #2d6a4f;
        }
        input[type="submit"] {
            background-color: #2d6a4f;
            color: #fff;
            border: none;
            padding: 12px;
            width: 100%;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #1b4332;
        }
        .error {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form action="login" method="post">
            <label>Username:</label>
            <input type="text" name="username" required>

            <label>Password:</label>
            <input type="password" name="password" required>

            <input type="submit" value="Login">
        </form>

        <!-- Show error only if it's set -->
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
    </div>
</body>
</html>
