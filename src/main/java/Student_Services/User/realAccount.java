package Student_Services.User;

public class realAccount extends Account{
    /**
     *
     * @author John Engh
     * @param username username field of database
     * @param password password field of database
     */
    protected realAccount(String username, String password) {
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
    public boolean getIsNull() {
        return false;
    }

    public boolean domainCheck() {
        return username.endsWith("csbsju.edu");
    }

    public String toString() {
        return "username: " + this.getUsername() + "\npassword: " + this.getPassword();
    }
}
