package Student_Services.User;

public class AccountFactory {
    public static Account newAccount(String username, String password) {
        if (username == null || password == null) {
            return new nullAccount();
        }
        else {
            return new realAccount(username, password);
        }
    }
}
