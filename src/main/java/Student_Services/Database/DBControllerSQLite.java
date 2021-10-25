package Student_Services.Database;

import Student_Services.User.Account;

public class DBControllerSQLite extends DBController{
    public DBControllerSQLite(String tableName) {
        super(tableName);
    }

    @Override
    public Account getAccount(String Username, String Table) {
        return null;
    }

    @Override
    public boolean createAccount(String Username, String Password, String Table) {
        return false;
    }
}
