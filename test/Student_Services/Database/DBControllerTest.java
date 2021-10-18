package Student_Services.Database;

import Student_Services.User.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBControllerTest {

    @BeforeEach
    void setUp() {
        // add table with dummy info
    }

    @Test
    public void testGetUserSuccess() {
        Account testAccount = new Account("test", "test");
        Account retrievedAccount = DBController.getAccount("test");
        assertTrue(testAccount.equals(retrievedAccount));


    }

    @AfterEach
    void tearDown() {
        //remove table
    }
}