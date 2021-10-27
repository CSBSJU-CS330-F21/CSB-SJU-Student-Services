package Student_Services.Database;

import Student_Services.User.Account;
import Student_Services.User.AccountFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @AfterAll
    static void tearDown() {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setDatabaseName("db.sqlite");
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement()) {
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
    public void test_Get_User_Successful() {
        String userinfo = "test1@csbsju.edu";
        String userpass = "test1";
        Account testAccount1 = AccountFactory.newAccount(userinfo, userpass);
        Account retrievedAccount1 = controller.getAccount(userinfo);
        assertEquals(testAccount1, retrievedAccount1);
    }

    @Test
    public void test_Get_User_Fail() {
        String userinfo = "failure";
        Account nullAccount = AccountFactory.newAccount(null, null);
        Account retrievedAccount1 = controller.getAccount(userinfo);
        assertEquals(nullAccount, retrievedAccount1);
    }

    @Test
    public void test_Create_Account_Successful() {
        String Userinfo = "create_test";
        Account createAccount1 = AccountFactory.newAccount(Userinfo, Userinfo);
        assertTrue(controller.createAccount(Userinfo, Userinfo));
        Account retrievedAccount1 = controller.getAccount(Userinfo);
        assertEquals(createAccount1, retrievedAccount1);
    }

    @ParameterizedTest(name="[{index}] username: {0} password: {1}")
    @CsvSource({
            "create_test_fail,''",
            "create_test_fail,",
            "'',create_test_fail",
            ",create_test_fail",
            "'',''",
            ","
    })
    public void test_Create_Account_Fail(String username, String pass) {
        assertFalse(controller.createAccount(username, pass));
    }

    @Test
    public void test_Create_Duplicate_Account_Fail() {
        assertFalse(controller.createAccount("test1@csbsju.edu", "test168745"));
        String Userinfo = "create_test_fail";
        assertTrue(controller.createAccount(Userinfo, Userinfo));
        assertFalse(controller.createAccount(Userinfo, Userinfo));
    }
}