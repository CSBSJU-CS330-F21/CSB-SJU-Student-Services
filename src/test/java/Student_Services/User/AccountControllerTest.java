package Student_Services.User;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class AccountControllerTest {
    private static final String tableName = "AccTestTable";

    @BeforeAll
    static void setUp() {
        // add table with dummy info
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

            String sql = "create table " + tableName + " (" + "username nvarchar(max) NOT NULL," + "user_password nvarchar(max) NOT NULL " + ");";

            stmt.execute(sql);

            for (int i = 1; i <= 20; i++) {
                String sql_ins = "insert into " + tableName + " values('test" + i + "@csbsju.edu" + "', 'test" + i + "')";
                stmt.execute(sql_ins);
            }


        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        AccountController.setUserTable(tableName);
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
    void testLoginUserSuccessful() {
        assertTrue(AccountController.loginUser("test1@csbsju.edu", "test1"));
        assertTrue(AccountController.loginUser("test2@csbsju.edu", "test2"));
        assertTrue(AccountController.loginUser("test3@csbsju.edu", "test3"));
        assertTrue(AccountController.loginUser("test4@csbsju.edu", "test4"));
        assertTrue(AccountController.loginUser("test5@csbsju.edu", "test5"));
        assertTrue(AccountController.loginUser("test6@csbsju.edu", "test6"));
    }

    @Test
    void testLoginUserPassFail() {
        assertFalse(AccountController.loginUser("test1@csbsju.edu", "fail"));
        assertFalse(AccountController.loginUser("test2@csbsju.edu", "fail"));
        assertFalse(AccountController.loginUser("test3@csbsju.edu", "fail"));
        assertFalse(AccountController.loginUser("test4@csbsju.edu", "fail"));
        assertFalse(AccountController.loginUser("test5@csbsju.edu", "fail"));
        assertFalse(AccountController.loginUser("test6@csbsju.edu", "fail"));
    }

    @Test
    void testLoginUserUsernameFail() {
        assertFalse(AccountController.loginUser("fail1@csbsju.edu", "test1"));
        assertFalse(AccountController.loginUser("fail2@csbsju.edu", "test2"));
        assertFalse(AccountController.loginUser("fail3@csbsju.edu", "test3"));
        assertFalse(AccountController.loginUser("fail4@csbsju.edu", "test4"));
        assertFalse(AccountController.loginUser("fail5@csbsju.edu", "test5"));
        assertFalse(AccountController.loginUser("fail6@csbsju.edu", "test6"));
    }
    @Test
    void testLoginUserUsernameDomainFail() {
        assertFalse(AccountController.loginUser("domainTest1@umn.edu", "test1"));
        assertFalse(AccountController.loginUser("domainTest2@gmail.com", "test2"));
        assertFalse(AccountController.loginUser("domainTest3", "test3"));
        assertFalse(AccountController.loginUser("domainTest4@", "test4"));
        assertFalse(AccountController.loginUser("domainTest5@yahoo.com", "test5"));
        assertFalse(AccountController.loginUser("domainTest6@csp.edu", "test6"));
    }

    @Test
    void testCreateUserSuccessful() {
        assertTrue(AccountController.createUser("createTest1@csbsju.edu", "test1"));
        assertTrue(AccountController.createUser("createTest2@csbsju.edu", "test2"));
    }

    @Test
    void testCreateUserFail() {
        assertFalse(AccountController.createUser("createTest1@umn.edu", "test1"));
        assertFalse(AccountController.createUser("createTest2@gmail.com", "test2"));
        assertFalse(AccountController.createUser("createTest3", "test3"));
        assertFalse(AccountController.createUser("createTest4@", "test4"));
        assertFalse(AccountController.createUser("createTest5@yahoo.com", "test5"));
        assertFalse(AccountController.createUser("createTest6@csp.edu", "test6"));
    }
}