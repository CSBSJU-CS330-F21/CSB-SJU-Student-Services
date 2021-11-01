package Student_Services.User;

public class nullAccount extends Account{

    protected nullAccount() {}

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
    public String toString() {
        return "null Account";
    }

    @Override
    public boolean domainCheck() {
        return false;
    }
}
