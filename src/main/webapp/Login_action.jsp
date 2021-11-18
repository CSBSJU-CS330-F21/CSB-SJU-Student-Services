<%--
  Created by IntelliJ IDEA.
  User: omavi
  Date: 10/19/2021
  Time: 12:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Student_Services.User.Account" %>
<%@ page import="Student_Services.User.AccountController" %>
<%@ page import="Student_Services.Listing.listingController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="Student_Services.Listing.listing" %>

<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    if (AccountController.loginUser(username, password)){
        Account acc = AccountController.getAccount(username);
        response.sendRedirect("Welcome.jsp");
        session.setAttribute("account", acc);
        List<listing> listings = new ArrayList<>();
        for (int i = 1; i < 15; i++) {
            if (listingController.getListing(i) != null) {
                listings.add(listingController.getListing(i));
            }
        }
        session.setAttribute("listings",listings);
        }

    else{
            response.sendRedirect("index.jsp?error=Your username or password is incorrect, please try again");

    }

%>