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
<%@ page import="Student_Services.Listing.listingController" %>
<%
    int id = Integer.parseInt(request.getParameter("listID"));
    String title = request.getParameter("listTitle");
    float price = Float.parseFloat(request.getParameter("listPrice"));
%>
<link rel="stylesheet" href="addProduct.css">
<section class="home-section">
    <div class="AddProduct">
        <h2>Edit Listing</h2>
        <form action="editListingAction.jsp" method="post">
            <h3>Edited Listing ID</h3>
            <input type="text" name="listId" value="<%=id%>"  readonly>
            <h3>Product/Service Name</h3>
            <input type="text" name="listName" value="<%=title%>" required>
            <h3>Price</h3>
            <input type="number" name="listPrice" min="1" value="<%=price%>"  step="any">
             <h3>Description</h3>
            <textarea name="listDescription" id="" style=width:350px;height:90px class="form-control" required> <%=listingController.getListing(id).getDescription()%> </textarea>
            <input type="submit" value="Edit Product" >
        </form>
    </div>
</section>


