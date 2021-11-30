package Student_Services.images;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;

public class image {
    private Blob imageFile;
    private int imageID;

    public image(Blob imageFile, int imageID) {
        this.imageFile = imageFile;
        this.imageID = imageID;
    }

    public Blob getImageFile() {
        return imageFile;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageFile(Blob imageFile) {
        this.imageFile = imageFile;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
