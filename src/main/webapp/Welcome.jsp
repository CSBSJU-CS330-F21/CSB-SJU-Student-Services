<!DOCTYPE html>
<%@ page import="Student_Services.User.Account" %>
<%@ page import="Student_Services.Listing.listing" %>
<%@ page import="Student_Services.Listing.listingController" %>
<%@ page import="java.util.List" %>
<%--<%@ page import ="Student_Services.Database.DBController"%>--%>
<%--<%@ page import ="Student_Services.User.AccountController"%>--%>
<%--<%@ page import="Student_Services.Database.DBControllerSQLServer" %>--%>
<%Account acc =  (Account) session.getAttribute("account");%>
<%List<listing> listings = listingController.getAllListings();%>
<%

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

<div class="sidebar">
    <div class="logo-details">
        <div class="logo_name">CSB|SJU <br> Student Services</div>
        <i class='bx bx-menu' id="btn" ></i>
    </div>
    <ul class="nav-list">
        <li>
            <i class='bx bx-search' ></i>
            <input type="text" placeholder="Search...">
            <span class="tooltip">Search</span>
        </li>
        <li>
            <a href="Welcome.jsp">
                <i class='bx bxs-home' ></i>
                <span class="links_name">Home</span>
            </a>
            <span class="tooltip">Home</span>
        </li>
        <li>
            <a href="#">
                <i class='bx bx-user' ></i>
                <span class="links_name">Account</span>
            </a>
            <span class="tooltip">Account</span>
        </li>
        <li>
            <a href="category.jsp">
                <i class='bx bx-archive' ></i>
                <span class="links_name">Categories</span>
            </a>
            <span class="tooltip">Categories</span>
        </li>
        <li>
            <a href="AddProduct.jsp">
                <i class='bx bxs-plus-square'></i>
                <span class="links_name">Add Listing</span>
            </a>
            <span class="tooltip">Add Listing</span>
        </li>
        <li>
            <a href="viewListing.jsp">
                <i class='bx bx-list-ul'></i>
                <span class="links_name">My Listings</span>
            </a>
            <span class="tooltip">My Listings</span>
        </li>
        <li class="profile">
            <div class="profile-details">
                <!--<img src="profile.jpg" alt="profileImg">-->
                <div class="name_job">
                    <%
                        String user;
                        if (acc.getFirst_name() == null || acc.getLast_name() == null) {
                            user = acc.getUsername();
                        }
                        else {
                            user = acc.getFirst_name() + " " + acc.getLast_name();
                        }
                    %>
                    <div class="name"><%=user%></div>
                    <div class="details">User Account</div>
                </div>
            </div>
            <br>
            <br>
            <br>
            <a href="Logout.jsp">
            <i class='bx bx-log-out' id="log_out" ></i>
            </a>
        </li>
    </ul>
</div>
<section class="home-section">
    <div class="text">Listings</div>
    <form action="viewListing.jsp" method="post">
    <select name="categories" id="categories">
        <option value="NewToOld">Date: Newest to Oldest</option>
        <option value="OldToNew">Date: Oldest to Newest</option>
        <option value="LowToHigh">Price: Low to High</option>
        <option value="HighToLow">Price: High to Low</option>
        <option value="Liked">Most Liked</option>
    </select>
    </form>
    <%
//        for (int i = 0; i < listings.size(); i++) {
        for (listing post: listings) {
    %>
    <div class="container">
        <div class="product">
            <div class="product-card">
                <h2 class="name"> <%= post.getTitle()%></h2>
                <span class="price"> $<%= String.format("%.2f", post.getPrice())%></span>
                <a class="popup-btn">View Listing</a>
                <img src="csbsju_logo.png" class="product-img" alt="">
                <span class="date"><i class='bx bxs-calendar'></i> Posted on: <%= post.getPost_date()%></span>
            </div>
            <div class="popup-view">
                <div class="popup-card">
                    <a><i class='bx bx-x close-btn'></i></a>
                    <div class="product-img">
                        <img src="csbsju_logo.png" alt="">
                    </div>
                    <div class="info">
<%--                        <%--%>
<%--                            Account a = AccountController.getAccount(listings.get(i).getAuthorID());--%>
<%--                        %>--%>
                        <h2><%= post.getTitle()%><br><span>User: <%= post.getAuthorName() %><br><%=post.getCatName()%></span></h2>
                        <p><%= post.getDescription()%></p>
                        <span class="price"> $<%= String.format("%.2f",post.getPrice())%></span>
                        <a href="#"> <i class='bx bxs-heart'></i> </a>
                    </div>
                </div>
            </div>
        </div> <% }    %>
   </div>
</section>
<script>
    let sidebar = document.querySelector(".sidebar");
    let closeBtn = document.querySelector("#btn");
    let searchBtn = document.querySelector(".bx-search");

    closeBtn.addEventListener("click", ()=>{
        sidebar.classList.toggle("open");
        menuBtnChange();//calling the function(optional)
    });

    searchBtn.addEventListener("click", ()=>{ // Sidebar open when you click on the search iocn
        sidebar.classList.toggle("open");
        menuBtnChange(); //calling the function(optional)
    });

    // following are the code to change sidebar button(optional)
    function menuBtnChange() {
        if(sidebar.classList.contains("open")){
            closeBtn.classList.replace("bx-menu", "bx-menu-alt-right");//replacing the iocns class
        }else {
            closeBtn.classList.replace("bx-menu-alt-right","bx-menu");//replacing the iocns class
        }
    }
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
