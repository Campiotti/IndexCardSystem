package persistence;

import model.Question;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionBroker extends Broker<Question>{
    @Override
    protected Question makeObject(ResultSet rs) throws SQLException {
        return null;
    }

    public List<Question> getQuestions() throws IOException, SQLException {
        return query("select * from question");
    }
    public void t(){
        createTables();
    }


}
