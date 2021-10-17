package Student_Services.Database;

import Student_Services.User.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {
    public static Account getAccount(String Username) {
        return new Account("test", "test");
    }

}
