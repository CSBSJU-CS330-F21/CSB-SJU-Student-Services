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
    int id = Integer.parseInt(request.getParameter("listId"));
    String title = request.getParameter("listName");
    float price = Float.parseFloat(request.getParameter("listPrice"));
    String desc = request.getParameter("listDescription");
    listing edit = listingController.getListing(id);
    edit.setTitle(title);
    edit.setPrice(price);
    edit.setDescription(desc);
    listingController.editListing(edit);


    List<listing> listings = new ArrayList<>();
    ArrayList<Integer> list = listingController.getAllListingIDs();
    for(Integer i : list) {
        listings.add(listingController.getListing(i));
    }

    session.setAttribute("listings",listings);
    response.sendRedirect("viewListing.jsp?error=Listing "+ id+ " has been successfully edited!");
%>