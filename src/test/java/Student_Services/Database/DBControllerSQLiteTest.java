package Student_Services.Database;

import Student_Services.User.Account;
import org.sqlite.SQLiteDataSource;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class DBControllerSQLiteTest {
    private static final String tableName = "dbTestTable";
    protected static DBControllerSQLite controller = new DBControllerSQLite(tableName);

    @BeforeAll
    static void setUp() {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:../db.sqlite");
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement()) {
//            remove any leftover table
            stmt.executeUpdate("drop table if exists " + tableName);

            String sql = "create table " + tableName + " (username nvarchar, user_password nvarchar);";

            stmt.executeUpdate(sql);

            PreparedStatement addUserRow = con.prepareStatement("insert into " + tableName + " values(?, ?)");

            for (int i = 1; i <= 20; i++) {
                addUserRow.setString(1, "test" + i + "@csbsju.edu");
                addUserRow.setString(2, "test" + i);
                addUserRow.addBatch();
            }
            addUserRow.executeBatch();


        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @AfterAll
//    static void tearDown() {
//        SQLiteDataSource ds = new SQLiteDataSource();
//        ds.setDatabaseName("db.sqlite");
//        try (Connection con = ds.getConnection();
//             Statement stmt = con.createStatement()) {
//            String sql = "drop table if exists " + tableName;
//            //remove  table
//            stmt.execute(sql);
//        }
//        // Handle any errors that may have occurred.
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void testGetUserSuccessful() {
        String userinfo = "test1@csbsju.edu";
        String userpass = "test1";
        Account testAccount1 = new Account(userinfo, userpass);
        Account retrievedAccount1 = controller.getAccount(userinfo);
        assertEquals(testAccount1, retrievedAccount1);
    }

    @Test
    public void testGetUserFail() {
        String userinfo = "failure";
        Account nullAccount = new Account(null, null);
        Account retrievedAccount1 = controller.getAccount(userinfo);
        assertEquals(nullAccount, retrievedAccount1);
    }

    @Test
    public void testCreateAccountSuccessful() {
        String Userinfo = "create_test";
        Account createAccount1 = new Account(Userinfo, Userinfo);
        assertTrue(controller.createAccount(Userinfo, Userinfo));
        Account retrievedAccount1 = controller.getAccount(Userinfo);
        assertEquals(createAccount1, retrievedAccount1);
    }

    @Test
    public void testCreateAccountFail() {
        String Userinfo = "create_test_fail";
        assertFalse(controller.createAccount(Userinfo, ""));
        assertFalse(controller.createAccount(Userinfo, null));
        assertFalse(controller.createAccount("", Userinfo));
        assertFalse(controller.createAccount(null, Userinfo));
        assertFalse(controller.createAccount("", ""));
        assertFalse(controller.createAccount(null, null));
    }
}