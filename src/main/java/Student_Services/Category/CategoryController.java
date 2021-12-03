package Student_Services.Category;

import Student_Services.Database.DBController;
import Student_Services.Database.DBControllerSQLServer;

import java.util.ArrayList;

public class CategoryController {
    static DBController dbController = new DBControllerSQLServer("categories");

    public static ArrayList<Category> getCategories() {
        return dbController.getCategories();
    }

    public static Category getCatByID(int categoryID) {
        return dbController.getCatByID(categoryID);
    }
}
