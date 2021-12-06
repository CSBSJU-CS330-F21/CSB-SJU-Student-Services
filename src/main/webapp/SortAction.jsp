<%@ page import="Student_Services.User.Account" %>
<%@ page import="Student_Services.User.AccountController" %>
<%@ page import="Student_Services.Listing.listingController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="Student_Services.Listing.listing" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%
    String sort = request.getParameter("sorting");
List<listing> listings = (List<listing>) session.getAttribute("listings");
if(sort.equals("Most Liked")) {
// to be implemented when like function is working

    session.setAttribute("sortedList", listings);
    response.sendRedirect("Welcome.jsp");
}
else if (sort.equals("Date: Newest to Oldest")) {

    Collections.sort(listings, Comparator.comparingDouble(listing ::getPostID));
    Collections.reverse(listings);

    session.setAttribute("sortedList", listings);
    response.sendRedirect("Welcome.jsp?sorted="+ sort);
}
else if (sort.equals("Date: Oldest to Newest")) {
    Collections.sort(listings, Comparator.comparingDouble(listing ::getPostID));

    session.setAttribute("sortedList", listings);
    response.sendRedirect("Welcome.jsp?sorted="+ sort);
}
else if (sort.equals("Price: Low to High")) {
    Collections.sort(listings, Comparator.comparingDouble(listing ::getPrice));

    session.setAttribute("sortedList", listings);
    response.sendRedirect("Welcome.jsp?sorted="+ sort);
}
else if (sort.equals("Price: High to Low")) {
    Collections.sort(listings, Comparator.comparingDouble(listing ::getPrice));
    Collections.reverse(listings);

    session.setAttribute("sortedList", listings);
    response.sendRedirect("Welcome.jsp?sorted="+ sort);
}
else {
    response.sendRedirect("Welcome.jsp");
}
%>


