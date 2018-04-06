package persistence;

import model.IndexCard;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class IndexCardBroker extends Broker<IndexCard>{
    private static IndexCardBroker instance;

    @Override
    protected IndexCard makeObject(ResultSet rs) {
        return null;
    }

    public static IndexCardBroker getInstance(){
        if(instance==null)
            instance= new IndexCardBroker();
        return instance;
    }

    private IndexCardBroker(){}

    public List<IndexCard> getQuestions() throws IOException, SQLException {
        return query("select * from question");
    }

}
