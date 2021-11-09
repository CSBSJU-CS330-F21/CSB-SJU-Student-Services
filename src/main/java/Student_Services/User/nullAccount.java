package Student_Services.User;

public class nullAccount extends Account{

    protected nullAccount() {}

    @Override
    public String getFirst_name() {
        return null;
    }

    @Override
    public String getLast_name() {
        return null;
    }

    @Override
    public int getUserID() {
        return 0;
    }

    @Override
    public String getUsername() {
        return "null";
    }

    @Override
    public String getPassword() {
        return "null";
    }

    @Override
    public boolean getIsNull() {
        return true;
    }

    @Override
    public accountType getAccountType() {
        return accountType.NULL;
    }

    @Override
    public String toString() {
        return "null Account";
    }

    @Override
    public boolean domainCheck() {
        return false;
    }
}
