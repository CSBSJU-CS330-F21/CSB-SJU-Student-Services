package Student_Services.User;

import Student_Services.Database.DBController;
import Student_Services.Database.DBControllerSQLServer;

/**
 * Login Controller gets an account username and verifies that the password associated is correct.
 */
public class AccountController {
    /**
     * Method verifies that username exists and password is correct.
     * @param username username provided by the user via log in page.
     * @param password password provided by the user via log in page.
     * @return boolean that verifies the username and password exists in the database
     */
    public static boolean loginUser(String username, String password) {
        DBController dbController = new DBControllerSQLServer("test");
        Account loginAccount = dbController.getAccount(username);
        if (loginAccount.getUsername() == null || loginAccount.getPassword() == null) {
            return false;
        }
        return (loginAccount.getUsername().equals(username)) && (loginAccount.getPassword().equals(password));
    }

}
