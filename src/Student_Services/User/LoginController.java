package Student_Services.User;

import Student_Services.Database.DBController;

public class LoginController {
    public static boolean loginUser(String username, String password ) {
        Account loginAccount = DBController.getAccount(username);
        return (loginAccount.getUsername().equals(username)) && (loginAccount.getPassword().equals(password));
    }

}
