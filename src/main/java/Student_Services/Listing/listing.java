package Student_Services.Listing;

import java.sql.Date;
import java.util.ArrayList;

public class listing {
    private String title;
    private String description;
    private int authorID;
    private int likes;
    private int imageID;
    private float price;
    private Date post_date;
    private ArrayList<Integer> tags;
    private int catID;
    private int postID;

    protected listing(String title, String description, int authorID, int likes, int imageID, float price, Date post_date, ArrayList<Integer> tags, int catID, int postID) {

        this.title = title;
        this.description = description;
        this.authorID = authorID;
        this.likes = likes;
        this.imageID = imageID;
        this.price = price;
        this.post_date = post_date;
        this.tags = tags;
        this.catID = catID;
        this.postID = postID;
    }

    public listing(String title, String description, int authorID, int imageID, float price, Date post_date, ArrayList<Integer> tags, int catID, int postID) {

        this.title = title;
        this.description = description;
        this.authorID = authorID;
        this.postID = postID;
        this.likes = 0;
        this.imageID = imageID;
        this.price = price;
        this.post_date = post_date;
        this.tags = tags;
        this.catID = catID;
    }

    public listing(String title, String description, int authorID, float price, Date post_date, int catID, int postID) {

        this.title = title;
        this.description = description;
        this.authorID = authorID;
        this.postID = postID;
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

    public listing(String title, String description, int authorID, float price, Date post_date, int postID) {

        this.title = title;
        this.description = description;
        this.authorID = authorID;
        this.price = price;
        this.post_date = post_date;
        this.postID = postID;
        this.likes = 0;
        this.imageID = -1;
        this.catID = -1;
        this.tags = new ArrayList<Integer>();
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

    public int getPostID() {
        return postID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public void setTags(ArrayList<Integer> tags) {
        this.tags = tags;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

}
