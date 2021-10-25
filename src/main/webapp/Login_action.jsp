<%--
  Created by IntelliJ IDEA.
  User: omavi
  Date: 10/19/2021
  Time: 12:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Student_Services.Database.DBControllerSQLServer" %>
<%@ page import="Student_Services.User.Account" %>
<%@ page import="Student_Services.User.AccountController" %>

<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    if (AccountController.loginUser(username, password)){
        response.sendRedirect("StandIn.jsp");
        //session.setAttribute("test", test);
    }

    else{
            response.sendRedirect("index.jsp?error=Your username or password is incorrect, please try again");

    }

%>