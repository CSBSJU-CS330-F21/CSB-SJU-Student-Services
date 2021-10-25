<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset = "utf-8">
    <title>Welcome Page</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="box area">
    <header>
        <div class=" wrapper">
            <div class="title">
                <a href="#">CSB|SJU Student Services</a>
            </div>
            <nav>
                <a href ="https://github.com/CSBSJU-CS330-F21/CSB-SJU-Student-Services">About Us</a>
                <a href ="#">Login</a>
                <a href ='Sign_Up.jsp'>Sign Up</a>
            </nav>
        </div>
    </header>
</div>
<div class="LoginBox">
    <img alt="" src="csbsju_logo.png" class="logo">
    <h2>User Login</h2>
    <form action="Login_action.jsp" method="post">
        <p>Username</p>
        <input type="text" name="username" placeholder="Enter Username" required>
        <p>Password</p>
        <input type="password" name="password" placeholder="Enter Password" required>
        <% if (request.getParameter("error") != null) {%>
        <h3> <%= request.getParameter("error") %> </h3>
        <% } %>
        <input type="submit" value="Login">
        <input type="button" value="Sign Up" onclick="window.location='Sign_Up.jsp'" >
    </form>
</div>
</body>
</html>