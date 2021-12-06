package Student_Services.images;

import Student_Services.Database.DBController;
import Student_Services.Database.DBControllerSQLServer;

import javax.sql.rowset.serial.SerialBlob;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;

public class imageController {
    private static String imageTable = "images";
    private static DBController imageDB = new DBControllerSQLServer(imageTable);
    private static void setImageTable(String table) {
        imageTable = table;


    }
    public static int addImage(InputStream imageFile) {
        return imageDB.addImage(imageFile);
    }
    public static Blob getImage(int imageID) {
        return imageDB.getImage(imageID).getImageFile();
    }
}
