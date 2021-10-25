package Student_Services.Database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

/**
 * @author johnengh
 * The database Controller is the go between for the SQL database and the java
 */
public class DBControllerSQLServer extends DBController {
    public DBControllerSQLServer(String tableName) {
        super(tableName);
    }

    /**
     * Creates a new database connection for query
     * @return new connection to the scrum-n-coke-db database
     * @throws SQLServerException passes exception up to calling method to throw error
     */
    @Override
    Connection createConnection() throws SQLServerException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("scrummy@scrum-n-coke");
        ds.setPassword("qwdluief3qvwt4o!");
        ds.setServerName("scrum-n-coke.database.windows.net");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("scrum-n-coke-db");
        return ds.getConnection();
    }

}
