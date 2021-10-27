package Student_Services.User;

import java.util.Objects;

public abstract class Account {

    protected String username;
    protected String password;


    public abstract String getUsername();

    public abstract String getPassword();

    public abstract boolean getIsNull();

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

    @Override
    public abstract String toString();

    public abstract boolean DomainCheck();
}
