package Student_Services.User;

import Student_Services.Database.DBController;
import Student_Services.Database.DBControllerSQLServer;

/**
 * Login Controller gets an account username and verifies that the password associated is correct.
 */
public class AccountController {
    static DBController dbController = new DBControllerSQLServer("users");
    /**
     * Method verifies that username exists and password is correct.
     * @param username username provided by the user via log in page.
     * @param password password provided by the user via log in page.
     * @return boolean that verifies the username and password exists in the database
     */
    public static boolean loginUser(String username, String password) {
        Account loginAccount = dbController.getAccount(username);
        if (loginAccount.getAccountType() == Account.accountType.NULL) {
            return false;
        }
        return (loginAccount.getUsername().equals(username)) && (loginAccount.getPassword().equals(password));
    }

    /**
     * @param username username of account to retrieve
     * @return returns account object with given username
     */
    public static Account getAccount(String username) {
        return dbController.getAccount(username);
    }

    /**
     * Method creates a new user account
     * @param username username to assign to account
     * @param password password to assign to account
     * @return returns true if account was successfully created
     */
    public static boolean createUser(String username, String password) {
       // if (!(usernameChecker(username) && passwordChecker(password))) {
      //      return false;
    //    }
        return dbController.createAccount(username, password);
    }

    /**
     * Method creates a new user account
     * @param username username to assign to account
     * @param password password to assign to account
     * @param first first name to assign to account
     * @param last last name to assign to account
     * @return returns true if account was successfully created
     */
    public static boolean createUser(String username, String password, String first, String last) {
        // if (!(usernameChecker(username) && passwordChecker(password))) {
        //      return false;
        //    }
        return dbController.createAccount(username, password, first, last);
    }

    /**
     * @param username username to check
     * @return true if username meets criteria
     */
    public static boolean usernameChecker(String username) {
        return (username.matches("^[a-zA-Z0-9+_.-]+@csbsju.edu$"));
    }

    /**
     * Checks if password meets criteria
     * @param password password to check
     * @return returns true if password criteria is met, otherwise false
     */
    public static boolean passwordChecker(String password) {
        return (password.length() > 7);
    }

    /**
     * Sets the table the database controller will use for user lookups
     * @param userTable table to use as user table
     */
    public static void setUserTable(String userTable) {
        dbController = new DBControllerSQLServer(userTable);
    }
//    public static boolean passwordChecker(String pass) {
//        if (pass.length() <= 7 || !pass.matches(".*\\d.*")) {
//            return false;
//        } else {
//            return true;
//        }
//    }
}
