package model;

import helper.ErrorLogger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import persistence.ConnectionManager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IndexCard extends BaseModel<IndexCard> implements IEntity {
    private SimpleIntegerProperty cardDeckFk = new SimpleIntegerProperty();
    private SimpleStringProperty question = new SimpleStringProperty();
    private SimpleStringProperty answer = new SimpleStringProperty();
    private SimpleBooleanProperty isNumberQuestion = new SimpleBooleanProperty();
    private SimpleStringProperty cardDeckName = new SimpleStringProperty();

    public IndexCard() throws SQLException {
        this.addProperty("cardDeckFk",this.cardDeckFk);
        this.addProperty("question",this.question);
        this.addProperty("answer",this.answer);
        this.addProperty("isNumberQuestion",this.isNumberQuestion);
    }
    public IndexCard(int cardDeckFk, String question, String answer, boolean isNumberQuestion) throws SQLException{
        this.addProperty("cardDeckFk",this.cardDeckFk);
        this.addProperty("question",this.question);
        this.addProperty("answer",this.answer);
        this.addProperty("isNumberQuestion",this.isNumberQuestion);
        this.cardDeckFk.set(cardDeckFk);
        this.question.set(question);
        this.answer.set(answer);
        this.isNumberQuestion.set(isNumberQuestion);

    }


    @Override
    public void save() {
        try {
            super.save();
        } catch (SQLException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    @Override
    public void view() {
        try {
            String sql = "SELECT * FROM INDEXCARD WHERE ID = "+this.id.get();
            Statement stmt = this.connection.createStatement();
            stmt.executeQuery(sql);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            this.cardDeckFk.set(rs.getInt(2));
            this.question.set(rs.getString(3));
            this.answer.set(rs.getString(4));
            this.isNumberQuestion.set(rs.getBoolean(5));
            cardDeckNameProperty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit() {
        super.update();
    }

    @Override
    public void patchData(Object[] data, boolean hasId) {
        int i=0;
        if(hasId){
            id.set(data[i].toString());
            i++;
        }
        try{this.cardDeckFk.set(Integer.parseInt(data[i].toString()));i++;
        this.question.set(data[i].toString());i++;
        this.answer.set(data[i].toString());i++;
        this.isNumberQuestion.set((boolean)data[i]);}
        catch (Exception e){
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    public int getCardDeckFk() {
        return cardDeckFk.get();
    }

    public SimpleIntegerProperty cardDeckFkProperty() {
        return cardDeckFk;
    }

    public void setCardDeckFk(int cardDeckFk) {
        this.cardDeckFk.set(cardDeckFk);
    }

    public String getQuestion() {
        return question.get();
    }

    public SimpleStringProperty questionProperty() {
        return question;
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public String getAnswer() {
        return answer.get();
    }

    public SimpleStringProperty answerProperty() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer.set(answer);
    }

    public boolean getIsNumberQuestion() {
        return isNumberQuestion.get();
    }

    public SimpleBooleanProperty isNumberQuestionProperty() {
        return isNumberQuestion;
    }

    public void setIsNumberQuestion(boolean isNumberQuestion) {
        this.isNumberQuestion.set(isNumberQuestion);
    }
    public boolean checkAnswer(String answer){
        boolean correct=false;
        if(getAnswer().equals(answer))
            correct=true;
        return correct;
    }

    public String getCardDeckName() {
        this.cardDeckName=cardDeckNameProperty();
        return cardDeckName.get();
    }

    private SimpleStringProperty cardDeckNameProperty() {
        try {
            String sql = "SELECT title from CARDDECK WHERE ID = "+getCardDeckFk();
            Statement stmt = ConnectionManager.getConnection().createStatement();
            stmt.executeQuery(sql);
            stmt.getResultSet().next();
            SimpleStringProperty tmp = new SimpleStringProperty();
            tmp.set(stmt.getResultSet().getString(1));
            return tmp;
        } catch (SQLException | IOException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
        return null;
    }

    public void setCardDeckName(String cardDeckName) {
        this.cardDeckName.set(cardDeckName);
    }
}
