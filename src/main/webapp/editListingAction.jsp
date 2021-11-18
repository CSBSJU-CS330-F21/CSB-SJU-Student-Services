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
Account acc =  (Account) session.getAttribute("account");
    int id = Integer.parseInt(request.getParameter("listId"));
    float price = Float.parseFloat(request.getParameter("listPrice"));
    String desc = request.getParameter("listDescription");
    String name = request.getParameter("listName");
    long millis = System.currentTimeMillis();
    java.sql.Date date = new java.sql.Date(millis);
    listing l = new listing(name, desc, acc.getUserID(), price, date, id);
   listingController.editListing(l);
    List<listing> listings = new ArrayList<>();
    for (int i = 1; i < 15; i++) {
        if (listingController.getListing(i) != null) {
            listings.add(listingController.getListing(i));
        }
    }

    session.setAttribute("listings",listings);
    response.sendRedirect("viewListing.jsp?error=Listing "+ id+ " has been successfully edited!");
%>