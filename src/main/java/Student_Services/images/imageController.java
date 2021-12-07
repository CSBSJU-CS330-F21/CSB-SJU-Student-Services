package Student_Services.images;

import Student_Services.Database.DBController;
import Student_Services.Database.DBControllerSQLServer;

import java.io.InputStream;

public class imageController {
    private static String imageTable = "images";
    private static DBController imageDB = new DBControllerSQLServer(imageTable);
    private static void setImageTable(String table) {
        imageTable = table;


    }
    public static int addImage(InputStream imageFile) {
        return imageDB.addImage(imageFile);
    }
    public static image getImage(int imageID) {
        return imageDB.getImage(imageID);
    }
}
