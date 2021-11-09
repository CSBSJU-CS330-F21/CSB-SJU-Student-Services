package Student_Services.User;

/**
 * Account factory makes new instances of accounts, either null or real, based of parameters supplied
 */
public class AccountFactory {
    /**
     * @param username username to assign to account object
     * @param password password to assign to account object
     * @return account object with supplied paramters
     */
    public static Account newAccount(String username, String password) {
        if (username == null || password == null) {
            return new nullAccount();
        }
        else {
            return new lookupAccount(username, password);
        }
    }
    public static Account newAccount(String username, String password, String first, String last, int userID) {
        if (username == null || password == null || userID < 1) {
            return new nullAccount();
        }
        else {
            return new realAccount(username, password, first, last, userID);
        }
    }
}
