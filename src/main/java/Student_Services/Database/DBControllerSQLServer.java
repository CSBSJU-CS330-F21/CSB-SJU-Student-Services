package Student_Services.Database;

import Student_Services.User.Account;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

/**
 * @author johnengh
 * The database Controller is the go between for the SQL database and the java
 */
public class DBControllerSQLServer extends DBController {
    private final boolean debug = false;

    public DBControllerSQLServer(String tableName) {
        super(tableName);
    }

    /**
     * Gets account from database, returns an account filled with null values if account doesn't exist
     * @param Username username of account to retrieve
     * @param Table table to use for account lookup
     * @return Account object filled with values from table if it exists, otherwise account filled with null values
     */
    public Account getAccount(String Username, String Table) {
        try (Connection con = createConnection()) {
            String query = "SELECT * FROM " + Table + " WHERE username= ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,Username);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return new Account(rs.getString(1), rs.getString(2));
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            if (debug) {
                e.printStackTrace();
            }
            return new Account(null, null);
        }

    }

    /**
     * @param Username Username to use for new account
     * @param Password password to use for new account
     * @param Table table to insert user into
     * @return account object with specified values
     */
    public boolean createAccount(String Username, String Password, String Table) {
        if (Username == null || Username.equals("") || Password == null || Password.equals("")) {
            return false;
        }
        try (Connection con = createConnection()) {
            String sql = "insert into " + Table + " values(?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, Username);
            pstmt.setString(2, Password);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            if (debug) {
                e.printStackTrace();
            }
            return false;
        }
    }

    /**
     * Creates a new database connection for query
     * @return new connection to the scrum-n-coke-db database
     * @throws SQLServerException
     */
    private Connection createConnection() throws SQLServerException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("scrummy@scrum-n-coke");
        ds.setPassword("qwdluief3qvwt4o!");
        ds.setServerName("scrum-n-coke.database.windows.net");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("scrum-n-coke-db");
        return ds.getConnection();
    }

}
