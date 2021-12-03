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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <jsp:include page="/sidebar/sidebar.jsp"></jsp:include>

<%List<Category> categories = CategoryController.getCategories();%>

<link rel="stylesheet" href="addProduct.css">
<section class="home-section">
    <div class="AddProduct">
        <h2>Create Listing</h2>
        <form action="AddProductAction.jsp" method="post">
            <h3>Product/Service Name</h3>
            <input type="text" name="productName" placeholder="Enter product/service" required>
            <h3>Price</h3>
            <input type="number" name="price" min="1" step="any">
            <h3>Category</h3>
            <select name="categories" id="categories">
                <%
                    for (Category cat: categories) {
                %>
                <option value= <%= cat.getCatID() %> ><%=cat.getName() %></option>
                <%
                    }
                %>
            </select>
            <br>
            <br>
            <h3>Image</h3>
            <input type="file" name="productImage" accept="image/png, image/jpg" class="custom-file-input" >
            <h3>Description</h3>
            <textarea name="description" id="" placeholder="Enter description of your product/service" style=width:350px;height:90px class="form-control" required></textarea>
            <input type="submit" value="Add Product" >
        </form>
    </div>
</section>


