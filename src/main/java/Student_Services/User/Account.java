package Student_Services.User;

import java.util.Objects;

public class Account {

    private final String username;
    private final String password;

    /**
     *
     * @author John Engh
     * @param username username field of database
     * @param password password field of database
     */
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (this.getClass() != o.getClass())
            return false;
        Account account = (Account) o;
        if (Objects.equals(account.getUsername(), this.getUsername()) && Objects.equals(account.getPassword(), this.getPassword())) {
            return true;
        }
        return  (this.getUsername().equals(account.getUsername()) && this.getPassword().equals(account.getPassword()));

    }


    public String toString() {
        return "username: " + this.getUsername() + "\npassword: " + this.getPassword();
    }

    public boolean DomainCheck(String username) {
        return username.endsWith("csbsju.edu");


    }
}
