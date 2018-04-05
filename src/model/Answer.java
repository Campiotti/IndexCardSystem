package model;

import helper.ErrorLogger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Answer extends BaseModel<Answer> implements IEntity,QandA {
    public SimpleIntegerProperty questionFk = new SimpleIntegerProperty();
    public SimpleStringProperty answer = new SimpleStringProperty();
    public SimpleBooleanProperty correct = new SimpleBooleanProperty();

    public Answer() throws SQLException {
        this.addProperty("questionFk",this.questionFk);
        this.addProperty("answer",this.answer);
        this.addProperty("correct",this.correct);
    }

    @Override
    public void save() {
        try {
            super.save();
        } catch (SQLException | IllegalAccessException | NoSuchMethodException | ClassNotFoundException | InvocationTargetException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete() {



    }

    @Override
    public void view(int id) {

        //todo get data from answerBroker and put it into variables.

    }

    @Override
    public void edit() {

    }

    @Override
    public Object getValue(String key) {
        return null;
    }

    @Override
    public void setText(String key, Object value) {

    }
}
