<!DOCTYPE html>
<%@ page import="Student_Services.User.Account" %>
<%@ page import="Student_Services.Listing.listing" %>
<%@ page import="java.util.List" %>
<%@ page import ="Student_Services.User.AccountController"%>
<%--<%@ page import="Student_Services.Database.DBControllerSQLServer" %>--%>
<%@ page import="Student_Services.Listing.listingController" %>
<jsp:include page="/sidebar/sidebar.jsp"></jsp:include>
<%Account acc =  (Account) session.getAttribute("account");
    List<listing> listings;
if (request.getParameter("sorted") != null) {
    listings = (List<listing>) session.getAttribute("sortedList");
}
else {
     listings = listingController.getAllListings();
}
%>

<html lang="en" dir="ltr">
<head>
    <title> Welcome Page </title>
    <meta charset="UTF-8">
    <!--<title> Responsive Sidebar Menu  | CodingLab </title>-->
    <link rel="stylesheet" href="welcome.css">
    <link rel="stylesheet" href="tester.css">
    <!-- Boxicons CDN Link -->
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<section class="home-section">
    <div class="text">Listings
        <% if (request.getParameter("sorted") != null) { %>
        | <%=request.getParameter("sorted")%>
        <% }%>
    </div>
    <form action="SortAction.jsp" method="post">
    <select name="sorting" id="sorting" onclick="this.form.submit()">
        <option value="Date: Oldest to Newest">Date: Oldest to Newest</option>
        <option value="Date: Newest to Oldest">Date: Newest to Oldest</option>
        <option value="Price: Low to High">Price: Low to High</option>
        <option value="Price: High to Low">Price: High to Low</option>
        <option value="Most Liked">Most Liked</option>
    </select>
    </form>
    <%
        for (int i = 0; i < listings.size(); i++) {

    %>
    <div class="container">
        <div class="product">
            <div class="product-card">
                <h2 class="name"> <%= listings.get(i).getTitle()%></h2>
                <span class="price"> $<%= String.format("%.2f",listings.get(i).getPrice())%></span>
                <a class="popup-btn">View Listing</a>
                <img src="/CSB_SJU_Student_Services_war_exploded/getImage/<%=listings.get(i).getImageID()%>" class="product-img" alt="" height="250px">
                <span class="date"><i class='bx bxs-calendar'></i> Posted on: <%= listings.get(i).getPost_date()%></span>
            </div>
            <div class="popup-view">
                <div class="popup-card">
                    <a><i class='bx bx-x close-btn'></i></a>
                    <div class="product-img">
                        <img src="/CSB_SJU_Student_Services_war_exploded/getImage/<%=listings.get(i).getImageID()%>" alt="">
                    </div>
                    <div class="info">
                        <%
                            Account a = AccountController.getAccount(listings.get(i).getAuthorID());
                        %>
                        <h2><%= listings.get(i).getTitle()%><br><span>Author's Name: <%= a.getFirst_name() + " " + a.getLast_name() %></span></h2>
                        <p><%= listings.get(i).getDescription()%></p>
                        <span class="price"> $<%= String.format("%.2f",listings.get(i).getPrice())%></span>
                        <a href="#"> <i class='bx bxs-heart'></i> </a>
                    </div>
                </div>
            </div>
        </div> <% }    %>
   </div>
</section>
<script>
    var popupViews = document.querySelectorAll('.popup-view');
    var popupBtns = document.querySelectorAll('.popup-btn');
    var closeBtns = document.querySelectorAll('.close-btn');

    //javascript for quick view button
    var popup = function(popupClick){
        popupViews[popupClick].classList.add('active');
    }

    popupBtns.forEach((popupBtn, i) => {
        popupBtn.addEventListener("click", () => {
            popup(i);
        });
    });

    //javascript for close button
    closeBtns.forEach((closeBtn) => {
        closeBtn.addEventListener("click", () => {
            popupViews.forEach((popupView) => {
                popupView.classList.remove('active');
            });
        });
    });
</script>
</body>
</html>
