package Student_Services.Listing;

import Student_Services.Database.DBController;
import Student_Services.Database.DBControllerSQLServer;

import java.util.ArrayList;

public class listingController {
    static DBController dbController = new DBControllerSQLServer("listings");
    public static boolean addListing(listing product) {
        return dbController.addListing(product);
    }

    public static listing getListing(int postID) {
        return dbController.getListing(postID);
    }

    public static void setlistTable(String listTable) {
        dbController = new DBControllerSQLServer(listTable);
    }

    public static boolean deleteListing(int postID) {
        return dbController.deleteListing(postID);
    }

    public static boolean editListing(listing Product) {
        return dbController.editListing(Product);
    }

    public static ArrayList<Integer> getAllListingIDs() {
        return dbController.getAllListingIDs();
    }

    public static ArrayList<listing> getAllListings() {
        return dbController.getAllListings();
    }

    public static ArrayList<listing> getAuthorListings(int authorID) {
        return dbController.getAuthorListings(authorID);
    }

}
