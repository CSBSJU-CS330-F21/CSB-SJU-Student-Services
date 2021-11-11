package Student_Services.Listing;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class listingControllerTest {
    private static final String tableName = "listTestTable";

    @BeforeAll
    static void setupAll() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("scrummy@scrum-n-coke");
        ds.setPassword("qwdluief3qvwt4o!");
        ds.setServerName("scrum-n-coke.database.windows.net");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("scrum-n-coke-db");
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
            stmt.executeUpdate("drop table if exists " + tableName);

            String sql = "create table " + tableName + " (" +
                    "listingID integer primary key identity," +
                    "author integer not null, " +
                    "title nvarchar(max) not null, " +
                    "description nvarchar(max) null, " +
                    "price float null, " +
                    "tags integer null, " +
                    "category integer null, " +
                    "image integer null, " +
                    "likes integer null, " +
                    "post_date datetime null" +
                    ")";
            stmt.executeUpdate(sql);
            stmt.executeUpdate("set IDENTITY_INSERT " + tableName + " ON");

            PreparedStatement addListingRow = con.prepareStatement("insert into " + tableName + "(author, title, description, price, listingID) values(?, ?, ?, ?, ?);");
            addListingRow.setInt(1, 3);
            addListingRow.setString(2, "test entry 1");
            addListingRow.setString(3, "a test entry");
            addListingRow.setFloat(4, (float) 1.1);
            addListingRow.setInt(5, 900);
            addListingRow.addBatch();

            addListingRow.setInt(1, 3);
            addListingRow.setString(2, "test entry 2");
            addListingRow.setString(3, "another test entry");
            addListingRow.setFloat(4, (float) 1.1);
            addListingRow.setInt(5, 910);
            addListingRow.addBatch();

            addListingRow.executeBatch();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        listingController.setlistTable(tableName);

    }

    @AfterAll
    static void teardownAll() {
        //remove table
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("scrummy@scrum-n-coke");
        ds.setPassword("qwdluief3qvwt4o!");
        ds.setServerName("scrum-n-coke.database.windows.net");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("scrum-n-coke-db");
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
            String sql = "drop table if exists " + tableName;
            //remove  table
            stmt.execute(sql);
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addListing() {
        listing testProduct = new listing("test 2", "this is a test object", 999, (float) 1.10, new Date(10), 450);
        assertTrue(listingController.addListing(testProduct));
    }

    @Test
    void getListing() {
        assertEquals(listingController.getListing(900).getPostID(), 900);

    }

    @Test
    void deleteListing() {
        assertTrue(listingController.deleteListing(910));
    }
}