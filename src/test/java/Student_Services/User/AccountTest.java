package Student_Services.User;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AccountTest {
    @ParameterizedTest
    @CsvSource({
            "test, test",
            "1, test",
            "sdfaghsassghjdhgjfdsf, test"
    })
    public void test_Get_Username(String username, String pass) {
        Account testAccount = new lookupAccount(username, pass);
        assertEquals(username, testAccount.getUsername());
    }
    @ParameterizedTest
    @CsvSource({
            "test, test",
            "test, 1",
            "test, sdfaghsassghjdhgjfdsf"
    })
    public void test_Get_Password(String username, String pass) {
        Account testAccount = new lookupAccount(username, pass);
        assertEquals(pass, testAccount.getPassword());
    }

}