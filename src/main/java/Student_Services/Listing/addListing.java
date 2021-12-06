package Student_Services.Listing;

import Student_Services.images.imageController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@WebServlet(name = "addListing", value = "/addListing")
@MultipartConfig
public class addListing extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listing listing = new listing();

        listing.setDescription(request.getParameter("description"));
        listing.setTitle(request.getParameter("productName"));
        listing.setPrice(Float.parseFloat(request.getParameter("price")));
        listing.setAuthorID(Integer.parseInt(request.getParameter("userID")));
        listing.setPost_date(new java.sql.Date(System.currentTimeMillis()));
        listing.setCatID(Integer.parseInt(request.getParameter("categoryPicker")));
        Part filePart = request.getPart("productImage"); // Retrieves <input type="file" name="file">
        int imageID = -1;
        if (filePart != null) {
            InputStream fileContent = filePart.getInputStream();
             imageID = imageController.addImage(fileContent);;
        }
        listing.setImageID(imageID);
        listingController.addListing(listing);
        response.sendRedirect("Welcome.jsp");
    }
}
