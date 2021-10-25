package Student_Services.Database;

import Student_Services.User.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
