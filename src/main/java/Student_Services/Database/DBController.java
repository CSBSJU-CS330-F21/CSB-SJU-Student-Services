package Student_Services.Database;

import Student_Services.User.Account;

public interface DBController {
    Account getAccount(String Username, String Table);
    Account getAccount(String Username);
    boolean createAccount(String Username, String Password, String Table);
    boolean createAccount(String Username, String Password);
}
