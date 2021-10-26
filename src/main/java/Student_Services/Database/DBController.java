package Student_Services.Database;

import Student_Services.User.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBController {
    protected final String tableName;
    private final boolean debug = false;

    public DBController(String tableName) {
        this.tableName = tableName;
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
            pstmt.setString(1, Username);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            con.close();
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

    public Account getAccount(String Username) {
        return getAccount(Username, tableName);
    }

    /**
     * creates new account from provided paramters
     * username and password must not be null or empty strings
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
            String sql = "insert into " + Table + "(username, user_password) values(?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, Username);
            pstmt.setString(2, Password);
            pstmt.execute();
            con.close();
            return true;
        } catch (SQLException e) {
            if (debug) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public boolean createAccount(String Username, String Password) {
        return createAccount(Username, Password, tableName);
    }
    abstract Connection createConnection() throws SQLException;
}
