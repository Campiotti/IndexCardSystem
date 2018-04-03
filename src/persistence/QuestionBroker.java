package persistence;

import model.Question;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionBroker extends Broker<Question>{
    private static QuestionBroker instance;

    @Override
    protected Question makeObject(ResultSet rs) throws SQLException {
        return null;
    }

    public static QuestionBroker getInstance(){
        if(instance==null)
            instance= new QuestionBroker();
        return instance;
    }

    private QuestionBroker(){}

    public List<Question> getQuestions() throws IOException, SQLException {
        return query("select * from question");
    }

}
