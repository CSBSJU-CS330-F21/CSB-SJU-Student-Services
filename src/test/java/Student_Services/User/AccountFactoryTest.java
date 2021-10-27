package Student_Services.User;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AccountFactoryTest {

    @ParameterizedTest(name="[{index}] username: {0} password: {1}")
    @CsvSource({
            "test,test",
            "test@csbsju.edu,test12345678"
    })
    void test_realAccount(String username, String pass) {
        Account testAccount = AccountFactory.newAccount(username, pass);
        assertFalse(testAccount.getIsNull());
    }

    @ParameterizedTest(name="[{index}] username: {0} password: {1}")
    @CsvSource({
            ",test",
            "test,",
            ","
    })
    public void test_nullAccount(String username, String pass) {
        Account testAccount = AccountFactory.newAccount(username, pass);
        assertTrue(testAccount.getIsNull());
    }

}