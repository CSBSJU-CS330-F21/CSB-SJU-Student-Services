package Student_Services.Database;

import Student_Services.User.Account;
import Student_Services.User.AccountFactory;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBController {
    protected final String userTable;
    protected final String imageTable = "images";
    private final boolean debug = true;

    public DBController(String tableName) {
        this.userTable = tableName;
    }

    /**
     * Gets account from database, returns an account filled with null values if account doesn't exist
     * @param Username username of account to retrieve
     * @return Account object filled with values from table if it exists, otherwise account filled with null values
     */
    public Account getAccount(String Username) {
        try (Connection con = createConnection()) {
            String query = "SELECT * FROM " + userTable + " WHERE username= ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, Username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return AccountFactory.newAccount(rs.getString("username"), rs.getString("user_password"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("userID"));
            }
            else {
                return AccountFactory.newAccount(null, null);
            }

        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            if (debug) {
                e.printStackTrace();
            }
            return null;
        }
    }


    /**
     * creates new account from provided parameters
     * username and password must not be null or empty strings
     * @param Username Username to use for new account
     * @param Password password to use for new account
     * @return boolean with success of creation
     */
    public boolean createAccount(String Username, String Password) {
        if (Username == null || Username.equals("") || Password == null || Password.equals("") || accountExists(Username)) {
            return false;
        }
        try (Connection con = createConnection()) {
            String sql = "insert into " + userTable + "(username, user_password) values(?, ?)";
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
     * creates new account from provided parameters
     * username and password must not be null or empty strings
     * @param Username Username to use for new account
     * @param Password password to use for new account
     * @return boolean with success of creation
     */
    public boolean createAccount(String Username, String Password, String first, String last) {
        if (Username == null || Username.equals("") || Password == null || Password.equals("") || accountExists(Username)) {
            return false;
        }
        try (Connection con = createConnection()) {
            String sql = "insert into " + userTable + "(username, user_password, first_name, last_name) values(?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, Username);
            pstmt.setString(2, Password);
            pstmt.setString(3,first);
            pstmt.setString(4, last);
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
     * checks if user account exists in database
     * @param Username username to check for in database
     * @return true if account is found
     */
    public boolean accountExists(String Username) {
        if (Username == null || Username.equals("")) {
            return false;
        }
        try (Connection con = createConnection()) {
            String query = "SELECT username FROM " + userTable + " WHERE username= ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, Username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
        catch (SQLException e) {
            if (debug) {
                e.printStackTrace();
            }
            return false;
        }
    }

    /**
     * adds an image file
     * @param imageFile serialized blob of image file
     * @return boolean of success of insert
     */
    public int addImage(SerialBlob imageFile) {
        if (imageFile == null) {
            return -1;
        }
        try (Connection con = createConnection()) {
            String query = "insert into " + imageTable + " values(?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setBlob(1, imageFile);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt("imageID");
        }
        catch (SQLException e) {
            if (debug) {
                e.printStackTrace();
            }
            return -1;
        }

    }

    public SerialBlob getImage(int imageID) {
        
    }

    abstract Connection createConnection() throws SQLException;
}
