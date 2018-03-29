package persistence;

import model.IndexCard;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IndexCardBroker extends Broker<IndexCard>{
    @Override
    protected IndexCard makeObject(ResultSet rs) throws SQLException {
        return null;
    }
}
