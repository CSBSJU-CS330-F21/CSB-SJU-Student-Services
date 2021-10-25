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
    String confirm =  request.getParameter("confirmPass");
    if (!confirm.equals(password)) {
        response.sendRedirect("Sign_Up.jsp?error=The passwords do not match!");
    }
    else if (!AccountController.passwordChecker(password)) {
        response.sendRedirect("Sign_Up.jsp?error=Password must be at least 8 characters long and have at least one digit");
    }
    else if (AccountController.createUser(username, password)){
        response.sendRedirect("StandIn.jsp");
        //session.setAttribute("test", test);
    }
    else{
        response.sendRedirect("Sign_Up.jsp?error=Please enter a valid username and password");
    }
%>
