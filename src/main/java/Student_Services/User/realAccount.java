package Student_Services.User;

public class realAccount extends Account{
    /**
     *
     * @author John Engh
     * @param username username field of database
     * @param password password field of database
     */
    protected realAccount(String username, String password, String first, String last, int userID) {
        this.username = username;
        this.password = password;
        this.first_name = first;
        this.last_name = last;
        this.userID = userID;
    }

    @Override
    public String getFirst_name() {
        return first_name;
    }

    @Override
    public String getLast_name() {
        return last_name;
    }

    @Override
    public int getUserID() {
        return userID;
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

    @Override
    public accountType getAccountType() {
        return accountType.REAL;
    }

    public boolean domainCheck() {
        return username.endsWith("csbsju.edu");
    }

    public String toString() {
        return "username: " + this.getUsername() + "\npassword: " + this.getPassword();
    }
}
