package model;

import helper.ErrorLogger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Answer extends BaseModel<Answer> implements IEntity {
    public SimpleIntegerProperty questionFk = new SimpleIntegerProperty();
    public SimpleStringProperty answer = new SimpleStringProperty();

    public Answer() throws SQLException {
        this.addProperty("questionFk",this.questionFk);
        this.addProperty("answer",this.answer);
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
    public void view(int id) {

        //todo get data from answerBroker and put it into variables.

    }

    @Override
    public void edit() {

    }
}
