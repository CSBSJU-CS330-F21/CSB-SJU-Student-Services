package Student_Services.Listing;

import java.sql.Date;
import java.util.ArrayList;

public class listing {
    private final String title;
    private final String description;
    private final int authorID;
    private final int likes;
    private final int imageID;
    private final float price;
    private final Date post_date;
    private final ArrayList<Integer> tags;
    private final int catID;

    protected listing(String title, String description, int authorID, int likes, int imageID, float price, Date post_date, ArrayList<Integer> tags, int catID) {

        this.title = title;
        this.description = description;
        this.authorID = authorID;
        this.likes = likes;
        this.imageID = imageID;
        this.price = price;
        this.post_date = post_date;
        this.tags = tags;
        this.catID = catID;
    }

    public listing(String title, String description, int authorID, int imageID, float price, Date post_date, ArrayList<Integer> tags, int catID) {

        this.title = title;
        this.description = description;
        this.authorID = authorID;
        this.likes = 0;
        this.imageID = imageID;
        this.price = price;
        this.post_date = post_date;
        this.tags = tags;
        this.catID = catID;
    }

    public listing(String title, String description, int authorID, float price, Date post_date, int catID) {

        this.title = title;
        this.description = description;
        this.authorID = authorID;
        this.likes = 0;
        this.imageID = -1;
        this.price = price;
        this.post_date = post_date;
        this.tags = new ArrayList<Integer>();
        this.catID = catID;
    }

    public listing(String title, String description, int authorID, float price, Date post_date) {

        this.title = title;
        this.description = description;
        this.authorID = authorID;
        this.likes = 0;
        this.imageID = -1;
        this.price = price;
        this.post_date = post_date;
        this.tags = new ArrayList<Integer>();
        this.catID = -1;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getAuthorID() {
        return authorID;
    }

    public int getLikes() {
        return likes;
    }

    public int getImageID() {
        return imageID;
    }

    public Date getPost_date() {
        return post_date;
    }

    public ArrayList<Integer> getTags() {
        return tags;
    }

    public int getCatID() {
        return catID;
    }

    public float getPrice() {
        return price;
    }
}
