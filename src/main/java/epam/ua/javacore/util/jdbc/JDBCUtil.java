package epam.ua.javacore.util.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
    public static Long getLastInsertId(Statement statement)throws SQLException {

        return statement.executeQuery("SELECT MAX(id) ").getLong(1);
    }
}
