package persistence;


import helper.ErrorLogger;

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
    protected abstract T makeObject(ResultSet rs) throws SQLException;

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

    /**
     * Creates all tables necessary for the program to run.
     */
    void createTables(){
        String tmp2="create table if not exists Question(id integer IDENTITY PRIMARY KEY auto_increment," +
                "cardDeckFk integer," +
                "question varchar(100)," +
                "isNumberQuestion bool," +
                "FOREIGN KEY(cardDeckFk) references CardDeck(id))";
        String tmp3="create table if not exists Answer(id integer IDENTITY PRIMARY KEY auto_increment," +
                "questionFk integer," +
                "answer varchar(100)," +
                "FOREIGN KEY(questionFk) references question(id))";
        String tmp1="create table if not exists CardDeck(id integer IDENTITY PRIMARY KEY auto_increment," +
                "title varchar(50)," +
                "passPercent integer," +
                "cardsPerRun integer)";
        try{
            update(tmp1);
            update(tmp2);
            update(tmp3);
        }catch (Exception e){
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }
}
