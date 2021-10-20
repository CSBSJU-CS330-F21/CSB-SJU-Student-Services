package Student_Services.User;

import Student_Services.Database.DBController;

/**
 * Login Controller gets an account username and verifies that the password associated is correct.
 */
public class LoginController {
    /**
     * Method verifies that username exists and password is correct.
     * @param username username provided by the user via log in page.
     * @param password password provided by the user via log in page.
     * @return boolean that verifies the username and password exists in the database
     */
    public static boolean loginUser(String username, String password ) {
        Account loginAccount = DBController.getAccount(username);
        return (loginAccount.getUsername().equals(username)) && (loginAccount.getPassword().equals(password));
    }

}
