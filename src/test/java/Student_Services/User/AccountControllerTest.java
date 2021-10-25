package Student_Services.User;

import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class AccountControllerTest {
    private static final String connectionUrl =
            "jdbc:sqlserver://scrum-coke.database.windows.net:1433;"
                    + "database=Student Services Database;"
                    + "user=developer@scrum-coke;"
                    + "password=Charge-Operator-Bush-Pupil-6;"
                    + "encrypt=true;"
                    + "trustServerCertificate=false;"
                    + "loginTimeout=30;";
    private static final String tableName = "test";
    //TODO Login controller uses main table instead of test table

    @BeforeAll
    static void setUp() {
        // add table with dummy info
        try (Connection con = DriverManager.getConnection(connectionUrl);
             Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
            //remove any leftover table
            stmt.executeUpdate("if object_id('" + tableName + "','U') is not null" + " drop table " + tableName);

            String sql = "create table " + tableName + " (" + "username nvarchar(max) NOT NULL," + "user_password nvarchar(max) NOT NULL " + ");";

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

    @Test
    void testLoginUserSuccessful() {
        assertTrue(AccountController.loginUser("test1", "test1"));
        assertTrue(AccountController.loginUser("test2", "test2"));
        assertTrue(AccountController.loginUser("test3", "test3"));
        assertTrue(AccountController.loginUser("test4", "test4"));
        assertTrue(AccountController.loginUser("test5", "test5"));
        assertTrue(AccountController.loginUser("test6", "test6"));
    }

    @Test
    void testLoginUserPassFail() {
        assertFalse(AccountController.loginUser("test1", "fail"));
        assertFalse(AccountController.loginUser("test2", "fail"));
        assertFalse(AccountController.loginUser("test3", "fail"));
        assertFalse(AccountController.loginUser("test4", "fail"));
        assertFalse(AccountController.loginUser("test5", "fail"));
        assertFalse(AccountController.loginUser("test6", "fail"));
    }

    @Test
    void testLoginUserUsernameFail() {
        assertFalse(AccountController.loginUser("fail1", "test1"));
        assertFalse(AccountController.loginUser("fail2", "test2"));
        assertFalse(AccountController.loginUser("fail3", "test3"));
        assertFalse(AccountController.loginUser("fail4", "test4"));
        assertFalse(AccountController.loginUser("fail5", "test5"));
        assertFalse(AccountController.loginUser("fail6", "test6"));
    }
}