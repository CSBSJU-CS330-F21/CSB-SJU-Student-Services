<%--
  Created by IntelliJ IDEA.
  User: omavi
  Date: 10/12/2021
  Time: 8:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="LoginBox">
    <h2>Sign Up</h2>
    <form action="SignUp_action.jsp" method="post">
        <p>Email</p>
        <input type="text" name="username" placeholder="Enter CSBSJU domain email" required>
        <p>Password</p>
        <input type="password" name="password" placeholder="Enter Password" required>
        <p>Confirm Password</p>
        <input type="password" name="confirmPass" placeholder="Confirm Password" required>
        <% if (request.getParameter("error") != null) {%>
        <h3> <%= request.getParameter("error") %> </h3>
        <% } %>
        <input type="submit" value="Create Account">
        <h3>Already have an account?</h3>
        <input type="button" value="Login" onclick="window.location='index.jsp'" >
    </form>
</div>

</body>
</html>
