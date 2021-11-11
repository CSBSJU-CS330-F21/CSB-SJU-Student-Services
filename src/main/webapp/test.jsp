<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="addProduct.css">
<%@ page import="Student_Services.User.Account" %>
<%Account acc =  (Account) session.getAttribute("account");%>
<%
        String productName = request.getParameter("productName");
        String category = request.getParameter("categories");
        String description =  request.getParameter("description");

        %>
<div class="cards">
    <div class="card">
        <img class="card__image" src="https://fakeimg.pl/400x300/009578/fff/" alt="">
        <div class="card__content">
            <p>
                <%= description %>
            </p>
            <p>
                <%= category %>
            </p>
        </div>
        <div class="card__info">
            <div>
                <p> Product Name is <%= productName %> </p>
            </div>
        </div>
    </div>
</div>