package persistence;

import model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IndexCardBroker extends Broker<Question>{
    @Override
    protected Question makeObject(ResultSet rs) throws SQLException {
        return null;
    }
}
