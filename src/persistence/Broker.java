package persistence;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains methods for CRUD functionality in the database
 * @author Campiotti
 * @param <T>
 * @version 1.0
 */
abstract class Broker<T> {
    /**
     * Creates an object out of a row of the sql-result.
     * These methods need to be used by subclasses.
     * @param rs result of a query.
     * @return object, which is the result of a row out of the query result.
     * @throws SQLException in case of a database access error.
     */
    protected abstract T makeObject(ResultSet rs);

    /**
     * Returns the result of an SQL query as a list.
     * @param sql Select instruction
     * @return a list of objects, each of those equal a row of the query-result
     * @throws IOException  /db.Properties could not be read
     * @throws SQLException error while accessing the database
     */
    List<T> query(String sql) throws IOException, SQLException {
        try (Statement stmt = ConnectionManager.getConnection()
                .createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            List<T> result = new ArrayList<>();
            while (rs.next()) {
                result.add(makeObject(rs));
            }

            return result;
        }
    }

    /**
     * Method to insert,update and delete.
     * @param sql INSERT, UPDATE, DELETE statement.
     * @throws IOException /db.properties could not be read.
     * @throws SQLException error while accessing the database.
     */
    void update(String sql) throws IOException, SQLException {
        try (Statement stmt = ConnectionManager.getConnection()
                .createStatement()) {

            stmt.executeUpdate(sql);
        }
    }


    /**
     * Inserts an entry with the return of a created primary key.
     * @param sql INSERT-instruction.
     * @return id , the primary key value of the row inserted.
     * @throws IOException /db.properties could not be read.
     * @throws SQLException error while accessing the database.
     */
    int insertAndReturnKey(String sql) throws IOException,
            SQLException {

        try (Statement stmt = ConnectionManager.getConnection()
                .createStatement()) {

            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        }
    }
}
