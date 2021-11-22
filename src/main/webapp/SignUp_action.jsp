<%--
  Created by IntelliJ IDEA.
  User: omavi
  Date: 10/21/2021
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Student_Services.Database.DBController" %>
<%@ page import="Student_Services.User.AccountController" %>

<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String fName = request.getParameter("fname");
    String lName = request.getParameter("lname");
    String confirm =  request.getParameter("confirmPass");
    if (!confirm.equals(password)) {
        response.sendRedirect("Sign_Up.jsp?error=The passwords do not match!");
    }
    else if (!AccountController.passwordChecker(password)) {
        response.sendRedirect("Sign_Up.jsp?error=Password must be at least 8 characters long");
    }
    else if (!AccountController.usernameChecker(username)){
        response.sendRedirect("Sign_Up.jsp?error=Only CSBSJU emails are allowed to sign up");
    }
    else if (AccountController.createUser(username, password, fName, lName)){
        response.sendRedirect("index.jsp?error= Account Created, Sign In Now");
    }
    else{
        response.sendRedirect("Sign_Up.jsp?error=Username already taken");
    }
%>
