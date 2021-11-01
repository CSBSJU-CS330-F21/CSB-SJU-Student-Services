<%--
  Created by IntelliJ IDEA.
  User: omavi
  Date: 10/31/2021
  Time: 6:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
</head>
<%
    session.invalidate();
    response.sendRedirect("index.jsp?error=Successfully Logged Out");
%>
</html>
