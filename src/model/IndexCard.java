package model;

import helper.ErrorLogger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class IndexCard extends BaseModel<IndexCard> implements IEntity {
    public SimpleIntegerProperty cardDeckFk = new SimpleIntegerProperty();
    public SimpleStringProperty question = new SimpleStringProperty();
    public SimpleStringProperty answer = new SimpleStringProperty();
    public SimpleBooleanProperty numberQuestion = new SimpleBooleanProperty();

    public IndexCard() throws SQLException {
        this.addProperty("cardDeckFk",this.cardDeckFk);
        this.addProperty("question",this.question);
        this.addProperty("answer",this.answer);
        this.addProperty("isNumberQuestion",this.numberQuestion);
    }
    public IndexCard(int cardDeckFk, String question, String answer, boolean numberQuestion) throws SQLException{
        this.addProperty("cardDeckFk",this.cardDeckFk);
        this.addProperty("question",this.question);
        this.addProperty("answer",this.answer);
        this.addProperty("isNumberQuestion",this.numberQuestion);
        this.cardDeckFk.set(cardDeckFk);
        this.question.set(question);
        this.answer.set(answer);
        this.numberQuestion.set(numberQuestion);

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
    public void view(int id) {

    }

    @Override
    public void edit() {

    }
}
