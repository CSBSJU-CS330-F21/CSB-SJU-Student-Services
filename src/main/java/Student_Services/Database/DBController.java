package Student_Services.Database;

import Student_Services.Listing.listing;
import Student_Services.User.Account;
import Student_Services.User.AccountFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBController {
    protected final String tableName;
    private final boolean debug = true;

    public DBController(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Gets account from database, returns an account filled with null values if account doesn't exist
     * @param Username username of account to retrieve
     * @return Account object filled with values from table if it exists, otherwise account filled with null values
     */
    public Account getAccount(String Username) {
        try (Connection con = createConnection()) {
            String query = "SELECT * FROM " + tableName + " WHERE username= ?;";
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
            String sql = "insert into " + tableName + "(username, user_password) values(?, ?)";
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
            String sql = "insert into " + tableName + "(username, user_password, first_name, last_name) values(?, ?, ?, ?)";
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
    public boolean accountExists(String Username) {
        if (Username == null || Username.equals("")) {
            return false;
        }
        try (Connection con = createConnection()) {
            String query = "SELECT username FROM " + tableName + " WHERE username= ?;";
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

    public boolean addListing(listing product) {
        try (Connection con = createConnection()) {
            String query = "insert into " + tableName + "(author, title, description, price, post_date) values(?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, product.getAuthorID());
            pstmt.setString(2, product.getTitle());
            pstmt.setString(3, product.getDescription());
            pstmt.setFloat(4, product.getPrice());
            pstmt.setDate(5, product.getPost_date());
            return pstmt.executeUpdate() > 0;
        }
        catch (SQLException e) {
            if (debug) {
                e.printStackTrace();
            }
            return false;
        }
    }
    abstract Connection createConnection() throws SQLException;

    public listing getListing(int postID) {
        try (Connection con = createConnection()) {
            String query = "SELECT * FROM " + tableName + " WHERE listingID= ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, postID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new listing(rs.getString("title"), rs.getString("description"), rs.getInt("author"), rs.getFloat("price"), rs.getDate("post_date"), postID);
            }
            else {
                return null;
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
    public boolean deleteListing(int postID) {
        try (Connection con = createConnection()) {
            String query = "DELETE FROM " + tableName + " WHERE postID= ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, postID);
            int result = pstmt.executeUpdate();
            return result > 0;
        }
        catch(SQLException e) {
            if (debug) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
