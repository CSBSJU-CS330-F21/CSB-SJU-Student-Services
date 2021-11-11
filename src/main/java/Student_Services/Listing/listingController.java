package Student_Services.Listing;

import Student_Services.Database.DBController;
import Student_Services.Database.DBControllerSQLServer;

public class listingController {
    static DBController dbController = new DBControllerSQLServer("listings");
    public static boolean addListing(listing product) {
        return dbController.addListing(product);
    }

    public static listing getListing(int postID) {
        return dbController.getListing(postID);
    }
}
