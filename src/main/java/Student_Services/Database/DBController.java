package Student_Services.Database;

import Student_Services.Category.Category;
import Student_Services.Listing.listing;
import Student_Services.User.Account;
import Student_Services.User.AccountFactory;
import Student_Services.images.image;

import java.sql.*;
import java.util.ArrayList;

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
     * Gets account from database, returns an account filled with null values if account doesn't exist
     * @param UserID username of account to retrieve
     * @return Account object filled with values from table if it exists, otherwise account filled with null values
     */
    public Account getAccount(int UserID) {
        try (Connection con = createConnection()) {
            String query = "SELECT * FROM " + tableName + " WHERE userID= ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, UserID);
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
            String query = "insert into " + tableName + "(author, title, description, price, post_date, category) values(?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, product.getAuthorID());
            pstmt.setString(2, product.getTitle());
            pstmt.setString(3, product.getDescription());
            pstmt.setFloat(4, product.getPrice());
            pstmt.setDate(5, product.getPost_date());
            if (product.getCatID() > 0) {
                pstmt.setInt(6, product.getCatID());
            }
            else {
                pstmt.setInt(6, -1);
            }
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
                listing post = new listing(rs.getString("title"), rs.getString("description"), rs.getInt("author"), rs.getFloat("price"), rs.getDate("post_date"), postID);
                post.setCatID(rs.getInt("category"));
                return post;
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
            String query = "DELETE FROM " + tableName + " WHERE listingID= ?;";
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
   public boolean editListing(listing Product) {
       try (Connection con = createConnection()) {
           String query = "UPDATE " + tableName + " SET title = ?, description = ?, price= ?, category =?" + " WHERE listingID= ?;";
           PreparedStatement pstmt = con.prepareStatement(query);
           pstmt.setString(1, Product.getTitle());
           pstmt.setString(2, Product.getDescription());
           pstmt.setFloat(3, Product.getPrice());
           if (Product.getCatID() > 0) {
               pstmt.setInt(4, Product.getCatID());
           }
           else {
               pstmt.setInt(4, -1);
           }
           pstmt.setInt(5, Product.getPostID());

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

    public ArrayList<Integer> getAllListingIDs() {
        try (Connection con = createConnection()) {
            String query = "SELECT (listingID) FROM " + tableName + ";";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Integer> postList = new ArrayList<>();
            while (rs.next()) {
                postList.add(rs.getInt("listingID"));
            }
            return postList;

        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            if (debug) {
                e.printStackTrace();
            }
            return null;
        }
    }

        public ArrayList<listing> getAllListings() {
            try (Connection con = createConnection()) {
                String query = "SELECT l.listingID, l.title, l.description, l.image, l.likes, l.price, l.post_date, u.first_name, u.last_name, c.catName FROM " + tableName + " l join categories c on c.catID = l.category join users u on u.userID = l.author;";
                PreparedStatement pstmt = con.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();
                ArrayList<listing> postList = new ArrayList<>();
                while (rs.next()) {
                    listing tempListing = new listing();
                    tempListing.setPostID(rs.getInt("listingID"));
                    tempListing.setTitle(rs.getString("title"));
                    tempListing.setDescription(rs.getString("description"));
                    tempListing.setLikes(rs.getInt("likes"));
                    tempListing.setPrice(rs.getFloat("price"));
                    tempListing.setImageID(rs.getInt("image"));
                    tempListing.setPost_date(rs.getDate("post_date"));
                    String authorName = String.join(" ", rs.getString("first_name"), rs.getString("last_name"));
                    tempListing.setAuthorName(authorName);
                    tempListing.setCatName(rs.getString("catName"));
                    postList.add(tempListing);
                }
                return postList;

            }
            // Handle any errors that may have occurred.
            catch (SQLException e) {
                if (debug) {
                    e.printStackTrace();
                }
                return null;
            }
        }

            public ArrayList<listing> getAuthorListings(int authorID) {
                try (Connection con = createConnection()) {
                    String query = "SELECT l.listingID, l.title, l.price, l.description, l.post_date, c.catName FROM " + tableName + " l join categories c on c.catID = l.category WHERE l.author= ?;";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setInt(1, authorID);
                    ResultSet rs = pstmt.executeQuery();
                    ArrayList<listing> postList = new ArrayList<>();
                    while (rs.next()) {
                        listing tempListing = new listing();
                        tempListing.setPostID(rs.getInt("listingID"));
                        tempListing.setTitle(rs.getString("title"));
                        tempListing.setDescription(rs.getString("description"));
                        tempListing.setPrice(rs.getFloat("price"));
                        tempListing.setPost_date(rs.getDate("post_date"));
                        tempListing.setCatName(rs.getString("catName"));
                        postList.add(tempListing);
                    }
                    return postList;

                }
                // Handle any errors that may have occurred.
                catch (SQLException e) {
                    if (debug) {
                        e.printStackTrace();
                    }
                    return null;
                }

    }
    public image getImage(int ImageID) {
        try (Connection con = createConnection()) {
            String query = "SELECT (imageID, image_file) FROM " + tableName + " WHERE imageID= ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            image imageobj = new image(null, -1);
            if (rs.next()) {
                imageobj.setImageID(rs.getInt("imageID"));
                imageobj.setImageFile(rs.getBlob("image_file"));
            }
            return imageobj;

        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            if (debug) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public int addImage(Blob imageFile) {
        try (Connection con = createConnection()) {
            String query = "insert into " + tableName + "(image_file) values(?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setBinaryStream(1, imageFile.getBinaryStream());

            return pstmt.executeUpdate();
        }
        catch (SQLException e) {
            if (debug) {
                e.printStackTrace();
            }
            return 0;
        }
    }

    public ArrayList<Category> getCategories() {
        try (Connection con = createConnection()) {
            String query = "SELECT * FROM " + tableName + ";";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Category> catList = new ArrayList<>();
            while (rs.next()) {
                Category tempCat= new Category();
                tempCat.setCatID(rs.getInt("catID"));
                tempCat.setName(rs.getString("catName"));
                catList.add(tempCat);
            }
            return catList;

        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            if (debug) {
                e.printStackTrace();
            }
            return null;
        }

    }

    public Category getCatByID(int categoryID) {
        try (Connection con = createConnection()) {
            String query = "SELECT * FROM " + tableName + " WHERE catID = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, categoryID);
            ResultSet rs = pstmt.executeQuery();
            Category tempCat= new Category();
            while (rs.next()) {
                tempCat.setCatID(rs.getInt("catID"));
                tempCat.setName(rs.getString("catName"));
            }
            return tempCat;

        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            if (debug) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public ArrayList<listing> getCatListings(int catID) {
        try (Connection con = createConnection()) {
            String query = "SELECT l.listingID, l.title, l.description, l.image, l.likes, l.price, l.post_date, u.first_name, u.last_name, c.catName FROM " + tableName + " l join categories c on c.catID = l.category join users u on u.userID = l.author WHERE category = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, catID);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<listing> postList = new ArrayList<>();
            while (rs.next()) {
                listing tempListing = new listing();
                tempListing.setPostID(rs.getInt("listingID"));
                tempListing.setTitle(rs.getString("title"));
                tempListing.setDescription(rs.getString("description"));
                tempListing.setLikes(rs.getInt("likes"));
                tempListing.setPrice(rs.getFloat("price"));
                tempListing.setImageID(rs.getInt("image"));
                tempListing.setPost_date(rs.getDate("post_date"));
                String authorName = String.join(" ", rs.getString("first_name"), rs.getString("last_name"));
                tempListing.setAuthorName(authorName);
                tempListing.setCatName(rs.getString("catName"));
                postList.add(tempListing);
            }
            return postList;

        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            if (debug) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
