import Student_Services.User.Account;

import java.sql.*;

public class SQLDatabaseConnection {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {
        String Username = "test1";
        String connectionUrl =
                "jdbc:sqlserver://scrum-coke.database.windows.net:1433;"
                        + "database=Student Services Database;"
                        + "user=developer@scrum-coke;"
                        + "password=Charge-Operator-Bush-Pupil-6;"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";

        try (Connection con = DriverManager.getConnection(connectionUrl);
             Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);) {
            // SELECT * FROM test WHERE username='test1';
            String query = String.format("SELECT * FROM test WHERE username='%s';", Username);
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            System.out.println("Username: " + rs.getString(1) + "\nPassword: " + rs.getString(2) );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}