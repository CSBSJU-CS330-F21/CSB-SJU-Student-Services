<%--
  Created by IntelliJ IDEA.
  User: dennisdean
  Date: 11/7/21
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/sidebar/sidebar.jsp"></jsp:include>

<%
    int id = Integer.parseInt(request.getParameter("listID"));
%>
<link rel="stylesheet" href="addProduct.css">
<section class="home-section">
    <div class="AddProduct">
        <h2>Edit Listing</h2>
        <form action="editListingAction.jsp" method="post">
            <h3>Edited Listing ID</h3>
            <input type="text" name="listId" value="<%=id%>"  readonly>
            <h3>Product/Service Name</h3>
            <input type="text" name="listName" placeholder="Enter product/service" required>
            <h3>Price</h3>
            <input type="number" name="listPrice" min="1" step="any">
             <h3>Description</h3>
            <textarea name="listDescription" id="" placeholder="Enter description of your product/service" style=width:350px;height:90px class="form-control" required></textarea>
            <input type="submit" value="Edit Product" >
        </form>
    </div>
</section>


