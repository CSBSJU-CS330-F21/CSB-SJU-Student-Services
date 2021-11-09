package Student_Services.User;

import java.util.Objects;

/**
 * Account object with parameters of username and password
 */
public abstract class Account {

    protected String username;
    protected String password;
    protected String first_name;
    protected String last_name;
    protected int userID;
    /**
     * returns the account type. either null, real, or lookup
     * @return account type of account
     */
    public abstract accountType getAccountType();

    /**
     * returns first name of user
     * @return first name of user
     */
    public abstract String getFirst_name();

    /**
     * returns the last name of the user
     * @return last name of user
     */
    public abstract String getLast_name();

    /**
     * returns userID assigned to account by the database
     * @return userID set by database
     */
    public abstract int getUserID();


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

    public enum accountType {
        NULL,
        LOOKUP,
        REAL
    }

    /**
     * compares username and password to check if two account objects are equal
     * @param o object to compare
     * @return true if objects are equal, false if they are not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (this.getClass().getGenericSuperclass() != o.getClass().getGenericSuperclass())
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
