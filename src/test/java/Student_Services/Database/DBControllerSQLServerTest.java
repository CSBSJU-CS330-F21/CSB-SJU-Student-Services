package Student_Services.Database;

import Student_Services.User.Account;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            stmt.addBatch("if object_id('" + tableName + "','U') is not null" + " drop table " + tableName);

            String sql = "create table " + tableName + " (" + "username nvarchar(max)," + "user_password nvarchar(max) " + ");";

            stmt.addBatch(sql);

            for (int i = 1; i <= 20; i++) {
                String sql_ins = "insert into " + tableName + " values('test" + i + "', 'test" + i + "')";
                stmt.addBatch(sql_ins);
            }
            stmt.executeBatch();


        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUserSuccessful() {
        String userinfo = "test1";
        Account testAccount1 = new Account(userinfo, userinfo);
        Account retrievedAccount1 = controller.getAccount(userinfo, tableName);
        assertEquals(testAccount1, retrievedAccount1);
    }

    @Test
    public void testGetUserFail() {
        String userinfo = "failure";
        Account nullAccount = new Account(null, null);
        Account retrievedAccount1 = controller.getAccount(userinfo, tableName);
        assertEquals(nullAccount, retrievedAccount1);
    }

    @Test
    public void testCreateAccountSuccessful() {
        String Userinfo = "create_test";
        Account createAccount1 = new Account(Userinfo, Userinfo);
        assertTrue(controller.createAccount(Userinfo, Userinfo, tableName));
        Account retrievedAccount1 = controller.getAccount(Userinfo, tableName);
        assertEquals(createAccount1, retrievedAccount1);
    }

    @Test
    public void testCreateAccountFail() {
        String Userinfo = "create_test_fail";
        assertFalse(controller.createAccount(Userinfo, "", tableName));
        assertFalse(controller.createAccount(Userinfo, null, tableName));
        assertFalse(controller.createAccount("", Userinfo, tableName));
        assertFalse(controller.createAccount(null, Userinfo, tableName));
        assertFalse(controller.createAccount("", "", tableName));
        assertFalse(controller.createAccount(null, null, tableName));
        //TODO add checks for usernames w/ spaces
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
}