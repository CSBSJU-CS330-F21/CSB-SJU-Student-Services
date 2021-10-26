package Student_Services.User;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AccountFactoryTest {

    @Test
    void test_realAccount() {
        Account testAccount1 = AccountFactory.newAccount("test", "test");
        assertFalse(testAccount1.getIsNull());
        Account testAccount2 = AccountFactory.newAccount("test@csbsju.edu", "test12345678");
        assertFalse(testAccount2.getIsNull());
    }

    @Test
    public void test_nullAccount() {
        Account testAccount2 = AccountFactory.newAccount(null, "test");
        assertTrue(testAccount2.getIsNull());
        Account testAccount3 = AccountFactory.newAccount("test", null);
        assertTrue(testAccount3.getIsNull());
        Account testAccount4 = AccountFactory.newAccount(null, null);
        assertTrue(testAccount4.getIsNull());
    }

}