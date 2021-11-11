<%--
  Created by IntelliJ IDEA.
  User: dennisdean
  Date: 11/7/21
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create a Listing for your Product/Service!</title>
    <link rel="stylesheet" type="text/css" href="addProduct.css">
</head>
<body>
<div class="AddProduct">
    <h2>Add Information for your Product/Service</h2>
    <form action="AddProduct_action.jsp" method="post">
        <h3>Product/Service Name</h3>
        <input type="text" name="productName" placeholder="Enter product/service" required>
        <h3>Description</h3>
        <input type="text" name="description" placeholder="Enter description of your product/service" required>
        <h3>Image</h3>
        <input type="file" name="productImage">
        <input type="submit" value="Add Product"
    </form>

</div>

</body>
</html>
