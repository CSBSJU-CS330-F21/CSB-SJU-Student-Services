package Student_Services.Database;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBControllerSQLite extends DBController{
    public DBControllerSQLite(String tableName) {
        super(tableName);
    }
    Connection createConnection() throws SQLException {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:../db.sqlite");
        return ds.getConnection();
    }
}
