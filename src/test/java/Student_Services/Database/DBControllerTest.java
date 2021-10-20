package Student_Services.Database;

import Student_Services.User.Account;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DBControllerTest {
    private static final String connectionUrl =
            "jdbc:sqlserver://scrum-coke.database.windows.net:1433;"
                    + "database=Student Services Database;"
                    + "user=developer@scrum-coke;"
                    + "password=Charge-Operator-Bush-Pupil-6;"
                    + "encrypt=true;"
                    + "trustServerCertificate=false;"
                    + "loginTimeout=30;";
    private static final String tableName = "dbTestTable";

    @BeforeAll
    static void setUp() {
        // add table with dummy info
        try (Connection con = DriverManager.getConnection(connectionUrl);
             Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
            //remove any leftover table
            stmt.executeUpdate("if object_id('" + tableName + "','U') is not null" + " drop table " + tableName);

            String sql = "create table " + tableName + " (" + "username varchar(max) NOT NULL," + "user_password varchar(max) NOT NULL " + ");";

            stmt.execute(sql);

            for (int i = 1; i <= 20; i++) {
                String sql_ins = "insert into " + tableName + " values('test" + i + "', 'test" + i + "')";
                stmt.execute(sql_ins);
            }


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
        Account retrievedAccount1 = DBController.getAccount(userinfo, tableName);
        assertEquals(testAccount1, retrievedAccount1);
    }

    @Test
    public void testGetUserFail() {
        String userinfo = "failure";
        Account failAccount1 = new Account(null, null);
        Account retrievedAccount1 = DBController.getAccount(userinfo, tableName);
        assertEquals(failAccount1, retrievedAccount1);
    }

    @Test
    public void testCreateAccountSuccessful() {
        String Userinfo = "create_test";
        Account createAccount1 = new Account(Userinfo, Userinfo);
        assertTrue(DBController.createAccount(Userinfo, Userinfo, tableName));
        Account retrievedAccount1 = DBController.getAccount(Userinfo, tableName);
        assertEquals(createAccount1, retrievedAccount1);
    }

    @Test
    public void testCreateAccountFail() {
        String Userinfo = "create_test_fail";
        assertFalse(DBController.createAccount(Userinfo, "", tableName));
        assertFalse(DBController.createAccount(Userinfo, null, tableName));
        assertFalse(DBController.createAccount("", Userinfo, tableName));
        assertFalse(DBController.createAccount(null, Userinfo, tableName));
        assertFalse(DBController.createAccount("", "", tableName));
        assertFalse(DBController.createAccount(null, null, tableName));
        //TODO add checks for usernames w/ spaces
    }

    @AfterAll
    static void tearDown() {
        //remove table
        try (Connection con = DriverManager.getConnection(connectionUrl);
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