package persistence;

import model.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerBroker extends Broker<Answer>{
    private static AnswerBroker instance;

    public AnswerBroker(){}


    @Override
    protected Answer makeObject(ResultSet rs) throws SQLException {
        return null;
    }
}
