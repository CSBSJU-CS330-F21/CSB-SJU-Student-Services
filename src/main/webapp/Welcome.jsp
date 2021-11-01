<%@ page import="Student_Services.User.Account" %><%--
  Created by IntelliJ IDEA.
  User: omavi
  Date: 10/28/2021
  Time: 12:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome User</title>
    <link rel="stylesheet" type="text/css" href="welcome.css">

</head>
<%Account acc =  (Account) session.getAttribute("account"); %>
<div class="bgimg">
    <div class="topleft">
    </div>
    <div class="middle">
        <h1>Welcome User <%=acc.getUsername()%></h1>
        <hr>
        <p>CSB|SJU Student Services</p>
        <a href="Logout.jsp" class="myButton">Logout</a>
    </div>
</div>
</html>
