package Student_Services.images;

import javax.sql.rowset.serial.SerialBlob;

public class image {
    private SerialBlob imageFile;
    private int imageID;

    public image(SerialBlob imageFile, int imageID) {
        this.imageFile = imageFile;
        this.imageID = imageID;
    }

    public SerialBlob getImageFile() {
        return imageFile;
    }

    public int getImageID() {
        return imageID;
    }
}
