package model;

import helper.ErrorLogger;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Question extends BaseModel<Question> implements IEntity,QandA {
    private int id;
    private int cardDeckFk;
    private String question;
    private boolean numberQuestion=false;

    public Question() throws SQLException {}


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


    @Override
    public Object getValue(String key) {
        return null;
    }

    @Override
    public void setText(String key, Object value) {

    }
}
