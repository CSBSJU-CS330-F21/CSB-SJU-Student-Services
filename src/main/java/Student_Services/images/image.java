package Student_Services.images;



public class image {
    private byte [] imageFile;
    private int imageID;

    public image(byte [] imageFile, int imageID) {
        this.imageFile = imageFile;
        this.imageID = imageID;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageFile(byte [] imageFile) {
        this.imageFile = imageFile;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
