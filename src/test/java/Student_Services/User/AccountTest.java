package Student_Services.User;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    public void testGetUsername() {
        Account testAccount1 = new Account("test", "test");
        assertTrue(testAccount1.getUsername().equals("test"));
        Account testAccount2 = new Account("1", "test");
        assertTrue(testAccount2.getUsername().equals("1"));
        Account testAccount3 = new Account("sdfaghjdfdsadghsfjhgfdsghgsfjdhsassghjdhgjfdsf", "test");
        assertTrue(testAccount3.getUsername().equals("sdfaghjdfdsadghsfjhgfdsghgsfjdhsassghjdhgjfdsf"));
    }
    @Test
    public void testGetPassword() {
        Account testAccount1 = new Account("test", "test");
        assertTrue(testAccount1.getPassword().equals("test"));
        Account testAccount2 = new Account("test", "1");
        assertTrue(testAccount2.getPassword().equals("1"));
        Account testAccount3 = new Account("test", "sdfaghjdfdsadghsfjhgfdsghgsfjdhsassghjdhgjfdsf");
        assertTrue(testAccount3.getPassword().equals("sdfaghjdfdsadghsfjhgfdsghgsfjdhsassghjdhgjfdsf"));
    }

}