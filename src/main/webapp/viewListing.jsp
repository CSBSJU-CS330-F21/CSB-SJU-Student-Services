<%@ page import="Student_Services.User.Account" %>
<%@ page import="Student_Services.Listing.listing" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<jsp:include page="/sidebar/sidebar.jsp"></jsp:include>
<link rel="stylesheet" href="Welcome.jsp">
<link rel="stylesheet" href="table.css">
<%Account acc =  (Account) session.getAttribute("account");%>
<%List<listing> listings = (List<listing>) session.getAttribute("listings");%>
<%List <listing> myListings = new ArrayList<>();%>
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
<%
    for(int i = 0; i < listings.size(); i++) {
        if (listings.get(i).getAuthorID() == acc.getUserID()) {
            myListings.add(listings.get(i));
        }
    }
%>
<section class = "home-section">
    <div class="text">My Listings</div>
<div class="table-wrapper">
    <table class="fl-table">
        <thead>
        <tr>
            <th>Listing id</th>
            <th>Name</th>
            <th>Price </th>
            <th>Date Posted </th>
            <th>Edit Listing</th>
            <th>Delete Listing</th>
        </tr>
        </thead>
        <tbody>
            <%
                for (int n = 0; n < myListings.size(); n++) {


            %>
        <form method="post" name="f1" action="deleteListing_action.jsp">
        <tr>
            <td>
                <%= myListings.get(n).getPostID()%>
                <input type="hidden" name="listID" value=<%= myListings.get(n).getPostID()%> />
            </td>
            <td>
                <%= myListings.get(n).getTitle()%>
                <input type="hidden" name="listTitle" value=<%= myListings.get(n).getTitle()%> />
            </td>
            <td>
                $<%= String.format("%.2f",myListings.get(n).getPrice())%>
                <input type="hidden" name="listPrice" value=<%= myListings.get(n).getPrice()%> />
            </td>
            <td>
                <%=myListings.get(n).getPost_date()%>
            </td>
            <td><button type="submit"class='bx bxs-edit' formaction="editListing.jsp" ></button></td>
            <td><button type="submit" class='bx bxs-x-circle'></button></td>

        </tr>
        </form>
            <%
                }
            %>
        <tbody>
    </table>
    <% if (request.getParameter("error") != null) {%>
    <h3> <%= request.getParameter("error") %> </h3>
    <% } %>
</div>
</section>
