package Student_Services.User;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AccountTest {
    @Test
    public void test_Get_Username() {
        Account testAccount1 = new realAccount("test", "test");
        assertEquals("test", testAccount1.getUsername());
        Account testAccount2 = new realAccount("1", "test");
        assertEquals("1", testAccount2.getUsername());
        Account testAccount3 = new realAccount("sdfaghjdfdsadghsfjhgfdsghgsfjdhsassghjdhgjfdsf", "test");
        assertEquals("sdfaghjdfdsadghsfjhgfdsghgsfjdhsassghjdhgjfdsf", testAccount3.getUsername());
    }
    @Test
    public void test_Get_Password() {
        Account testAccount1 = new realAccount("test", "test");
        assertEquals("test", testAccount1.getPassword());
        Account testAccount2 = new realAccount("test", "1");
        assertEquals("1", testAccount2.getPassword());
        Account testAccount3 = new realAccount("test", "sdfaghjdfdsadghsfjhgfdsghgsfjdhsassghjdhgjfdsf");
        assertEquals("sdfaghjdfdsadghsfjhgfdsghgsfjdhsassghjdhgjfdsf", testAccount3.getPassword());
    }

}