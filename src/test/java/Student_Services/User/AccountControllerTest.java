package Student_Services.User;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
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
            //stmt.executeUpdate("if object_id('" + tableName + "','U') is not null drop table " + tableName);
            stmt.executeUpdate("drop table if exists " + tableName);

            String sql = "create table " + tableName + " (username nvarchar(max) NOT NULL, user_password nvarchar(max) NOT NULL);";

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
    void test_Login_User_Successful() {
        assertTrue(AccountController.loginUser("test1@csbsju.edu", "test1"));
        assertTrue(AccountController.loginUser("test2@csbsju.edu", "test2"));
        assertTrue(AccountController.loginUser("test3@csbsju.edu", "test3"));
        assertTrue(AccountController.loginUser("test4@csbsju.edu", "test4"));
        assertTrue(AccountController.loginUser("test5@csbsju.edu", "test5"));
        assertTrue(AccountController.loginUser("test6@csbsju.edu", "test6"));
    }

    @Test
    void test_Login_User_Pass_Fail() {
        assertFalse(AccountController.loginUser("test1@csbsju.edu", "fail"));
        assertFalse(AccountController.loginUser("test2@csbsju.edu", "fail"));
        assertFalse(AccountController.loginUser("test3@csbsju.edu", "fail"));
        assertFalse(AccountController.loginUser("test4@csbsju.edu", "fail"));
        assertFalse(AccountController.loginUser("test5@csbsju.edu", "fail"));
        assertFalse(AccountController.loginUser("test6@csbsju.edu", "fail"));
    }

    @Test
    void test_Login_User_Username_Fail() {
        assertFalse(AccountController.loginUser("fail1@csbsju.edu", "test1"));
        assertFalse(AccountController.loginUser("fail2@csbsju.edu", "test2"));
        assertFalse(AccountController.loginUser("fail3@csbsju.edu", "test3"));
        assertFalse(AccountController.loginUser("fail4@csbsju.edu", "test4"));
        assertFalse(AccountController.loginUser("fail5@csbsju.edu", "test5"));
        assertFalse(AccountController.loginUser("fail6@csbsju.edu", "test6"));
    }
    @Test
    void test_Login_User_Username_Domain_Fail() {
        assertFalse(AccountController.loginUser("domainTest1@umn.edu", "test1"));
        assertFalse(AccountController.loginUser("domainTest2@gmail.com", "test2"));
        assertFalse(AccountController.loginUser("domainTest3", "test3"));
        assertFalse(AccountController.loginUser("domainTest4@", "test4"));
        assertFalse(AccountController.loginUser("domainTest5@yahoo.com", "test5"));
        assertFalse(AccountController.loginUser("domainTest6@csp.edu", "test6"));
    }

    @Test
    void test_Create_User_Successful() {
        assertTrue(AccountController.createUser("createTest1@csbsju.edu", "test12345678"));
        assertTrue(AccountController.createUser("createTest2@csbsju.edu", "test22345690"));
    }

    @Test
    void test_Create_User_Fail_Bad_Domain() {
        assertFalse(AccountController.createUser("createTest1@umn.edu", "test1"));
        assertFalse(AccountController.createUser("createTest2@gmail.com", "test2"));
        assertFalse(AccountController.createUser("createTest3", "test3"));
        assertFalse(AccountController.createUser("createTest4@", "test4"));
        assertFalse(AccountController.createUser("createTest5@yahoo.com", "test5"));
        assertFalse(AccountController.createUser("createTest6@csp.edu", "test6"));
        //TODO add checks for usernames w/ spaces
    }

    @Test
    void test_Create_User_Fail_Spaces() {
        assertFalse(AccountController.createUser("space test1", "test1"));
        assertFalse(AccountController.createUser(" spaceTest2", "test1"));
        assertFalse(AccountController.createUser(" spaceTest3 ", "test1"));
        assertFalse(AccountController.createUser("spaceTest3 ", "test1"));
    }

    @Test
    void test_Create_User_Fail_Semicolon() {
        assertFalse(AccountController.createUser("semicolon;test1", "test1"));
        assertFalse(AccountController.createUser(";semicolonTest2;", "test1"));
        assertFalse(AccountController.createUser(";semicolonTest3;", "test1"));
        assertFalse(AccountController.createUser("semicolonTest4;", "test1"));
    }

    @Test
    void test_Password_Checker_Good() {
        assertTrue(AccountController.passwordChecker("12345678"));
        assertTrue(AccountController.passwordChecker("qwertyuiop"));
        assertTrue(AccountController.passwordChecker("ghvarvnai;wo"));
        assertTrue(AccountController.passwordChecker("nusrte@vwaberytwve"));
        assertTrue(AccountController.passwordChecker("aaaaaaaa"));
        assertTrue(AccountController.passwordChecker("5432*^&12345678"));
        assertTrue(AccountController.passwordChecker("qwert^(#%yuiop"));
        assertTrue(AccountController.passwordChecker("ghvar}{:vnai;wo"));
        assertTrue(AccountController.passwordChecker("nusrt|:{_*e@vwaberytwve"));
        assertTrue(AccountController.passwordChecker("aaaaaaaaaaaäaaa"));
    }

    @Test
    void test_Password_Checker_Bad() {
        assertFalse(AccountController.passwordChecker("aaaaaaa"));
        assertFalse(AccountController.passwordChecker(""));
        assertFalse(AccountController.passwordChecker("1"));
        assertFalse(AccountController.passwordChecker("12"));
        assertFalse(AccountController.passwordChecker("123"));
        assertFalse(AccountController.passwordChecker("1234"));
        assertFalse(AccountController.passwordChecker("12345"));
        assertFalse(AccountController.passwordChecker("123456"));
        assertFalse(AccountController.passwordChecker("1234567"));
    }
}