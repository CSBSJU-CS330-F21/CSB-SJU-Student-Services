<!DOCTYPE html>
<%@ page import="Student_Services.User.Account" %>
<%@ page import="Student_Services.Listing.listing" %>
<%@ page import="Student_Services.Listing.listingController" %>
<%@ page import="java.util.List" %>
<%@ page import="Student_Services.Category.CategoryController" %>
<%@ page import="Student_Services.Category.Category" %>
<jsp:include page="/sidebar/sidebar.jsp"></jsp:include>
<%
    Account acc =  (Account) session.getAttribute("account");
    int categoryID = 1;
    if (session.getAttribute("postCategory") != null) {
        categoryID = (int) session.getAttribute("postCategory");
    }
    Category pageCat = CategoryController.getCatByID(categoryID);
    List<listing> listings = listingController.getCatListings(categoryID);
    List<Category> categories = CategoryController.getCategories();
%>

<html lang="en" dir="ltr">
<head>
    <title> Category: <%=pageCat.getName()%> </title>
    <meta charset="UTF-8">
    <!--<title> Responsive Sidebar Menu  | CodingLab </title>-->
    <link rel="stylesheet" href="welcome.css">
    <link rel="stylesheet" href="tester.css">
    <!-- Boxicons CDN Link -->
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

</body>
</html>
