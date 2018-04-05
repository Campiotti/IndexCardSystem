package model;

import helper.ErrorLogger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Question extends BaseModel<Question> implements IEntity {
    public SimpleIntegerProperty cardDeckFk;
    private SimpleStringProperty question;
    private SimpleBooleanProperty numberQuestion;

    public Question() throws SQLException {
        this.addProperty("cardDeckFk",this.cardDeckFk);
        this.addProperty("question",this.question);
        this.addProperty("numberQuestion",this.numberQuestion);
    }
    public Question(int cardDeckFk, String question, boolean numberQuestion) throws SQLException{
        this.addProperty("cardDeckFk",this.cardDeckFk);
        this.addProperty("question",this.question);
        this.addProperty("numberQuestion",this.numberQuestion);
        this.cardDeckFk.set(cardDeckFk);
        this.question.set(question);
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
