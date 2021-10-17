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
}
