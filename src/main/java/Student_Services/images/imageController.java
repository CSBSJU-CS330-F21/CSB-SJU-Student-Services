package Student_Services.images;

import Student_Services.Database.DBController;
import Student_Services.Database.DBControllerSQLServer;

import javax.sql.rowset.serial.SerialBlob;

public class imageController {
    private static String imageTable = "images";
    private static DBController imageDB = new DBControllerSQLServer(imageTable);
    private static void setImageTable(String table) {
        imageTable = table;


    }
    public int addImage(SerialBlob imageFile) {
        return imageDB.addImage(imageFile);
    }
    public Blob getImage(int imageID) {
        return imageDB.getImage(imageID);
    }
}
