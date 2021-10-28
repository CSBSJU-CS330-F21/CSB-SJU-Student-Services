package Student_Services.Database;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Database controller for interacting with sqlite database
 */
public class DBControllerSQLite extends DBController{
    /**
     * instantiates new sqlite database controller
     * @param tableName table to use for user list
     */
    public DBControllerSQLite(String tableName) {
        super(tableName);
    }

    /**
     * creates a new connection object to the sqlite database
     * @return connection to sqlite database
     * @throws SQLException will throw exception if database connection is unsuccessful
     */
    Connection createConnection() throws SQLException {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:../db.sqlite");
        return ds.getConnection();
    }
}
