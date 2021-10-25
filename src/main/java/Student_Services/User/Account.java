package Student_Services.User;
public class Account {

    private String username;
    private String password;

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
        if (account.getUsername() == this.getUsername() && account.getPassword() == this.getPassword()) {
            return true;
        }
        return  (this.getUsername().equals(account.getUsername()) && this.getPassword().equals(account.getPassword()));

    }

    public boolean DomainCheck(String username) {
        if (username.endsWith("csbsju.edu")) {
            return true;
        }
        else {
            return false;
        }


    }
}
