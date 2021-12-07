package Student_Services.images;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "getImage", value = "/getImage/*")
public class getImage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageName = request.getPathInfo().substring(1);
        int imageID = Integer.parseInt(imageName);
        image imageObj = imageController.getImage(imageID);
        if (imageObj != null && imageObj.getImageFile() != null) {
            byte [] content = imageObj.getImageFile();
            response.setContentType(getServletContext().getMimeType(imageName));
            response.setContentLength(content.length);
            response.getOutputStream().write(content);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
