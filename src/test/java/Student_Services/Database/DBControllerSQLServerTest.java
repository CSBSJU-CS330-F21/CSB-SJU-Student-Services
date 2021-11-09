package Student_Services.Database;

import Student_Services.User.Account;
import Student_Services.User.AccountFactory;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

class DBControllerSQLServerTest {

    private static final String tableName = "dbTestTable";
    protected static DBControllerSQLServer controller = new DBControllerSQLServer(tableName);

    @BeforeAll
    static void setUp() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("scrummy@scrum-n-coke");
        ds.setPassword("qwdluief3qvwt4o!");
        ds.setServerName("scrum-n-coke.database.windows.net");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("scrum-n-coke-db");
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
            //remove any leftover table
            stmt.executeUpdate("if object_id('" + tableName + "','U') is not null" + " drop table " + tableName);

            String sql = "create table " + tableName + " (" +
                    "userID integer primary key identity," +
                    "username nvarchar(max)," +
                    "user_password nvarchar(max), " +
                    "first_name nvarchar(max) null, " +
                    "last_name nvarchar(max) null" +
                    ")";

            stmt.executeUpdate(sql);

            PreparedStatement addUserRow = con.prepareStatement("insert into " + tableName + "(username, user_password, first_name, last_name) values(?, ?, ?, ?)");

            for (int i = 1; i <= 5; i++) {
                addUserRow.setString(1, "test" + i + "@csbsju.edu");
                addUserRow.setString(2, "test" + i);
                addUserRow.setString(3, "test" + i);
                addUserRow.setString(4, "test" + i);
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
        //remove table
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("scrummy@scrum-n-coke");
        ds.setPassword("qwdluief3qvwt4o!");
        ds.setServerName("scrum-n-coke.database.windows.net");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("scrum-n-coke-db");
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
            String sql = "drop table " + tableName;
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
        assertTrue(controller.createAccount(Userinfo, Userinfo), "Couldn't create account");
        Account retrievedAccount1 = controller.getAccount(Userinfo);
        assertEquals(createAccount1, retrievedAccount1, "Account did not match expected account");
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
        assertFalse(controller.createAccount("test1@csbsju.edu", "test1"));
        String Userinfo = "create_test_fail";
        assertTrue(controller.createAccount(Userinfo, Userinfo));
        assertFalse(controller.createAccount(Userinfo, Userinfo));
    }
}