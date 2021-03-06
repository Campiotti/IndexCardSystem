package persistence;

import helper.ErrorLogger;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager{

    private static DBManager instance;
    private DBManager(){}

    public static DBManager getInstance(){
        if(instance==null)
            instance = new DBManager();
        return instance;
    }

    public void seed(){
        createTables();
    }

    public void clearDatabase(){
        deleteTables();
        createTables();
    }

    /**
     * Creates all tables necessary for the program to run.
     */
    private void createTables(){
        String tmp2="create table if not exists IndexCard(id integer IDENTITY PRIMARY KEY auto_increment," +
                "cardDeckFk integer," +
                "question varchar(100)," +
                "answer varchar(100)," +
                "isNumberQuestion bool," +
                "FOREIGN KEY(cardDeckFk) references CardDeck(id) ON DELETE CASCADE)";
        /*String tmp3="create table if not exists Answer(id integer IDENTITY PRIMARY KEY auto_increment," +
                "questionFk integer," +
                "answer varchar(100)," +
                "FOREIGN KEY(questionFk) references question(id) on delete cascade)";*/
        String tmp1="create table if not exists CardDeck(id integer IDENTITY PRIMARY KEY auto_increment," +
                "title varchar(50)," +
                "passPercent integer," +
                "cardsPerRun integer)";
        try{
            this.update(tmp1);
            this.update(tmp2);
            //update(tmp3);
        }catch (Exception e){
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    /**
     * Drops all tables necessary for the program to run. (only needs to delete two because of on delete cascade)
     */
    private void deleteTables(){
        try{
            update("drop table if exists IndexCard");
            update("drop table if exists CardDeck");
        }catch (Exception e){
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    /**
     * Method to insert,update and delete.
     * @param sql INSERT, UPDATE, DELETE statement.
     * @throws IOException /db.properties could not be read.
     * @throws SQLException error while accessing the database.
     */
    private void update(String sql) throws IOException, SQLException {
        try (Statement stmt = ConnectionManager.getConnection()
                .createStatement()) {

            stmt.executeUpdate(sql);
        }
    }

}
