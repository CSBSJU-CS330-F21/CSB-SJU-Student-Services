package Student_Services.Database;

import Student_Services.User.Account;

public abstract class DBController {
    protected final String tableName;

    public DBController(String tableName) {
        this.tableName = tableName;
    }

    public abstract Account getAccount(String Username, String Table);

    public Account getAccount(String Username) {
        return getAccount(Username, tableName);
    }
    public abstract boolean createAccount(String Username, String Password, String Table);

    public boolean createAccount(String Username, String Password) {
        return createAccount(Username, Password, tableName);
    }
}
