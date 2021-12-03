<%@ page import="Student_Services.Listing.listingController" %>
<%@ page import="Student_Services.User.Account" %>
<%@ page import="Student_Services.Listing.listing" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%!

%><%Account acc =  (Account) session.getAttribute("account");%>
<%
        String title = request.getParameter("productName");
        String categoryString = request.getParameter("categories");
        int category = Integer.parseInt(categoryString);
        String description = request.getParameter("description");
        String mon = request.getParameter("price");
        float price = Float.parseFloat(mon);
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        listing listing = new listing(title, description, acc.getUserID(), price, date);
        listing.setCatID(category);
    listingController.addListing(listing);
//    List<listing> listings = new ArrayList<>();
//    for (int i = 1; i < 15; i++) {
//        if (listingController.getListing(i) != null) {
//            listings.add(listingController.getListing(i));
//        }
//    }
//    session.setAttribute("listings",listings);
    response.sendRedirect("Welcome.jsp");
%>