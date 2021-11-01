package Student_Services.User;

import java.util.Objects;

/**
 * Account object with parameters of username and password
 */
public abstract class Account {

    protected String username;
    protected String password;


    /**
     * gets username of account object
     * @return username of account
     */
    public abstract String getUsername();

    /**
     * gets password of account object
     * @return password of account
     */
    public abstract String getPassword();

    /**
     * gets if object is null Account or real Account
     * @return null status of object
     */
    public abstract boolean getIsNull();

    /**
     * compares username and password to check if two account objects are equal
     * @param o object to compare
     * @return true if objects are equal, false if they are not
     */
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

    /**
     * converts object to string representation
     * @return string representation of object
     */
    @Override
    public abstract String toString();

    /**
     * checks if username matches the csbsju.edu domain
     * @return true if domain check is passed
     */
    public abstract boolean domainCheck();
}
