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
<%@ page import="Student_Services.Listing.listing" %>
<%@ page import="Student_Services.Listing.listingController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/sidebar/sidebar.jsp"></jsp:include>

<%
    int id = Integer.parseInt(request.getParameter("listID"));
    listing post = listingController.getListing(id);
    List<Category> categories = CategoryController.getCategories();
%>
<link rel="stylesheet" href="addProduct.css">
<section class="home-section">
    <div class="AddProduct">
        <h2>Edit Listing</h2>
        <form action="editListingAction.jsp" method="post">
            <input type="text" name="listId" value="<%=id%>"  readonly hidden>
            <h3>Product/Service Name</h3>
            <input type="text" name="listName" placeholder="Enter product/service" value="<%=post.getTitle()%>" required>
            <h3>Price</h3>
            <input type="number" name="listPrice" min="1" step="any" value=<%=post.getPrice()%>>
            <h3>Category</h3>
            <select name="categories" id="categories" value=<%=post.getCatID()%>>
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
             <h3>Description</h3>
            <textarea name="listDescription" id="" placeholder="Enter description of your product/service" style=width:350px;height:90px class="form-control" value="<%=post.getDescription()%>"required></textarea>
            <input type="submit" value="Edit Product" >
        </form>
    </div>
</section>


