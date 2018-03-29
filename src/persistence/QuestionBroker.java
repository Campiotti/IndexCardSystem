package persistence;

import model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionBroker extends Broker<Question>{
    @Override
    protected Question makeObject(ResultSet rs) throws SQLException {
        return null;
    }
}
