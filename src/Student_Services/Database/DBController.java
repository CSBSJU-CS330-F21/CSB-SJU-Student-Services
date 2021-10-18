package Student_Services.Database;

import Student_Services.User.Account;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;

import com.microsoft.sqlserver.jdbc.SQLServerResultSet;

import microsoft.sql.DateTimeOffset;

public class DBController {
    public static Account getAccount(String Username) {
        String connectionUrl = "jdbc:sqlserver://<server>:<port>;databaseName=<database>;user=<user>;password=<password>";

        try (Connection con = DriverManager.getConnection(connectionUrl);
             Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);) {
            // SELECT * FROM test WHERE username='test1';
            String query = String.format("SELECT * FROM test WHERE username='%s';", Username);
            ResultSet rs = stmt.executeQuery(query);
            return new Account("test", "test");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Account("null", "null");
        }

    }

}
