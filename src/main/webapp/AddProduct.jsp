<%--
  Created by IntelliJ IDEA.
  User: dennisdean
  Date: 11/7/21
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page import="Student_Services.Category.Category" %>
<%@ page import="Student_Services.Category.CategoryController" %>
<%@ page import="Student_Services.User.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <jsp:include page="/sidebar/sidebar.jsp"></jsp:include>
<%List<Category> categories = CategoryController.getCategories();%>
<%Account acc =  (Account) session.getAttribute("account");%>
<link rel="stylesheet" href="addProduct.css">
<head>
</head>
<section class="home-section">
    <div class="AddProduct">
        <h2>Create Listing</h2>
        <form action="addListing" method="post" enctype="multipart/form-data">
            <h3>Product/Service Name</h3>
            <input type="text" name="productName" placeholder="Enter product/service" required>
            <h3>Price</h3>
            <input type="number" name="price" min="1" step="any">
            <h3>Category</h3>
            <select name="categoryPicker" id="categoryPicker">
                <%
                    for (Category cat: categories) {
                %>
                <option value= <%= cat.getCatID() %> ><%=cat.getName() %></option>
                <% } %>
            </select>
            <h3>Image</h3>
            <input type="file" name="productImage" accept="image/png, image/jpg" class="custom-file-input" >
            <h3>Description</h3>
            <textarea name="description" id="description" placeholder="Enter description of your product/service" style=width:350px;height:90px class="form-control" required></textarea>
            <input type="number" name="userID" value="<%=acc.getUserID()%>" hidden>
            <input type="submit" value="Add Product" >
        </form>
          </div>
</section>

