<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Invalidate the current session
    if (session != null) {
        session.invalidate();
    }

    // Redirect to login page after logout
    response.sendRedirect(request.getContextPath() + "/login.jsp");
%>
