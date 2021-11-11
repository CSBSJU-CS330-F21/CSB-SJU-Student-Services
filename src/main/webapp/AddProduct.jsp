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
    <h2>Create Listing</h2>
    <form action="test.jsp" method="post">
        <h3>Product/Service Name</h3>
        <input type="text" name="productName" placeholder="Enter product/service" required>
        <h3>Category</h3>
        <select name="categories" id="categories">
            <option value="Outdoor">Sports & Outdoors</option>
            <option value="Food">Food & Kitchen</option>
            <option value="Entertainment">Entertainment</option>
            <option value="Electronics">Electronics</option>
        </select>
        <br>
        <br>
        <h3>Image</h3>
        <input type="file" name="productImage" class="custom-file-input" >
        <h3>Description</h3>
        <textarea name="description" id="" placeholder="Enter description of your product/service" style=width:350px;height:90px class="form-control" required></textarea>
        <input type="submit" value="Add Product" onclick="window.location='test.jsp'" >>
    </form>

</div>

</body>
</html>
