package model;

import helper.ErrorLogger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import persistence.ConnectionManager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CardDeck extends BaseModel<CardDeck> implements IEntity {

    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleIntegerProperty passPercent = new SimpleIntegerProperty();
    private SimpleIntegerProperty cardsPerRun = new SimpleIntegerProperty();
    private SimpleIntegerProperty cardCount = new SimpleIntegerProperty();

    private List<IndexCard> indexCardList = new ArrayList<>();

    public CardDeck() throws SQLException {
        this.addProperty("title",this.title);
        this.addProperty("passPercent",this.passPercent);
        this.addProperty("cardsPerRun",this.cardsPerRun);
    }
    public CardDeck(String title, int passPercent, int cardsPerRun) throws SQLException{
        this.addProperty("title",this.title);
        this.addProperty("passPercent",this.passPercent);
        this.addProperty("cardsPerRun",this.cardsPerRun);
        this.title.set(title);
        this.passPercent.set(passPercent);
        this.cardsPerRun.set(cardsPerRun);

    }

    @Override
    public void save() {
        try {
            super.save();
        } catch (SQLException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public void view(int id) {

    }

    @Override
    public void edit() {

    }

    public int getQuestionsCount(){
        if(this.id!=null){
            String sql = "SELECT COUNT(ID) FROM INDEXCARD WHERE CARDDECKFK = "+id.get();
            try {
                Statement stmt = ConnectionManager.getConnection().createStatement();
                stmt.executeQuery(sql);
                while(stmt.getResultSet().next())
                return stmt.getResultSet().getInt(1);
            } catch (SQLException | IOException e) {
                ErrorLogger.getInstance().log(e.getLocalizedMessage());
            }
        }
        return 0;
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public int getPassPercent() {
        return passPercent.get();
    }

    public SimpleIntegerProperty passPercentProperty() {
        return passPercent;
    }

    public void setPassPercent(int passPercent) {
        this.passPercent.set(passPercent);
    }

    public int getCardsPerRun() {
        return cardsPerRun.get();
    }

    public SimpleIntegerProperty cardsPerRunProperty() {
        return cardsPerRun;
    }

    public void setCardsPerRun(int cardsPerRun) {
        this.cardsPerRun.set(cardsPerRun);
    }

    public int getCardCount() {
        return cardCount.get();
    }

    public SimpleIntegerProperty cardCountProperty() {
        SimpleIntegerProperty tmp = new SimpleIntegerProperty();
        tmp.set(getQuestionsCount());
        return tmp;
    }

    public void setCardCount(int cardCount) {
        //placeholder method
    }
}
