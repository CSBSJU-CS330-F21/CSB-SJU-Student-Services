package Student_Services.Database;

import Student_Services.User.Account;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;

/**
 * @author johnengh
 * The database Controller is the go between for the SQL database and the java
 */
public class DBController {
    private static final String tableName = "test";

    /**
     * Gets account from database, returns an account filled with null values if account doesn't exist
     * @param Username username of account to retrieve
     * @param Table table to use for account lookup
     * @return Account object filled with values from table if it exists, otherwise account filled with null values
     */
    public static Account getAccount(String Username, String Table) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("developer@scrum-coke");
        ds.setPassword("Charge-Operator-Bush-Pupil-6");
        ds.setServerName("scrum-coke.database.windows.net");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Student Services Database");
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
            String query = String.format("SELECT * FROM %s WHERE username='%s';", Table, Username);
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return new Account(rs.getString(1), rs.getString(2));
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            //e.printStackTrace();
            return new Account(null, null);
        }

    }

    /**
     * @param Username username of account to retrieve
     * @return Account object filled with values from table if it exists, otherwise account filled with null values
     */
    public static Account getAccount(String Username) {
        return getAccount(Username, tableName);
    }

    /**
     * @param Username Username to use for new account
     * @param Password password to use for new account
     * @param Table table to insert user into
     * @return account object with specified values
     */
    public static boolean createAccount(String Username, String Password, String Table) {
        if (Username == null || Username.equals("") || Password == null || Password.equals("")) {
            return false;
        }
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("developer@scrum-coke");
        ds.setPassword("Charge-Operator-Bush-Pupil-6");
        ds.setServerName("scrum-coke.database.windows.net");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Student Services Database");
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
            String sql = String.format("insert into " + Table + " values('%s', '%s')", Username, Password);
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * @param Username Username to use for new account
     * @param Password password to use for new account
     * @return account object with specified values
     */
    public static boolean createAccount(String Username, String Password) {
        return createAccount(Username, Password, tableName);
    }

}
