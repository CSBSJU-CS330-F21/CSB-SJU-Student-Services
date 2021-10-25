package Student_Services.Database;

import Student_Services.User.Account;

public class DBControllerSQLite implements DBController{
    @Override
    public Account getAccount(String Username, String Table) {
        return null;
    }

    @Override
    public Account getAccount(String Username) {
        return null;
    }

    @Override
    public boolean createAccount(String Username, String Password, String Table) {
        return false;
    }

    @Override
    public boolean createAccount(String Username, String Password) {
        return false;
    }
}
