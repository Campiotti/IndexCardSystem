package model;

import helper.ErrorLogger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class CardDeck extends BaseModel<CardDeck> implements IEntity {

    public SimpleStringProperty title;
    public SimpleIntegerProperty passPercent;
    public SimpleIntegerProperty cardsPerRun;

    public CardDeck() throws SQLException {
        this.addProperty("title",this.title);
        this.addProperty("passPercent",this.passPercent);
        this.addProperty("cardsPerRun",this.cardsPerRun);
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
