package model;

import helper.ErrorLogger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDeck extends BaseModel<CardDeck> implements IEntity {

    public SimpleStringProperty title = new SimpleStringProperty();
    public SimpleIntegerProperty passPercent = new SimpleIntegerProperty();
    public SimpleIntegerProperty cardsPerRun = new SimpleIntegerProperty();

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
}
