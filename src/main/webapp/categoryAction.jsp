<%--
  Created by IntelliJ IDEA.
  User: omavi
  Date: 10/19/2021
  Time: 12:01 AM
  To change this template use File | Settings | File Templates.
--%>

<%
    String categoryString = request.getParameter("postCategory");
    int category = Integer.parseInt(categoryString);
    session.setAttribute("postCategory",category);
    response.sendRedirect("category.jsp");
%>