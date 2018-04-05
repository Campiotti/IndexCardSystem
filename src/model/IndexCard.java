package model;

import helper.ErrorLogger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class IndexCard extends BaseModel<IndexCard> implements IEntity {
    private SimpleIntegerProperty cardDeckFk = new SimpleIntegerProperty();
    private SimpleStringProperty question = new SimpleStringProperty();
    private SimpleStringProperty answer = new SimpleStringProperty();
    private SimpleBooleanProperty numberQuestion = new SimpleBooleanProperty();

    public IndexCard() throws SQLException {
        this.addProperty("cardDeckFk",this.cardDeckFk);
        this.addProperty("question",this.question);
        this.addProperty("answer",this.answer);
        this.addProperty("isNumberQuestion",this.numberQuestion);
    }
    public IndexCard(int cardDeckFk, String question, String answer, boolean numberQuestion) throws SQLException{
        this.addProperty("cardDeckFk",this.cardDeckFk);
        this.addProperty("question",this.question);
        this.addProperty("answer",this.answer);
        this.addProperty("isNumberQuestion",this.numberQuestion);
        this.cardDeckFk.set(cardDeckFk);
        this.question.set(question);
        this.answer.set(answer);
        this.numberQuestion.set(numberQuestion);

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
    public void view(int id) {

    }

    @Override
    public void edit() {

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

    public boolean isNumberQuestion() {
        return numberQuestion.get();
    }

    public SimpleBooleanProperty numberQuestionProperty() {
        return numberQuestion;
    }

    public void setNumberQuestion(boolean numberQuestion) {
        this.numberQuestion.set(numberQuestion);
    }
    public boolean checkAnswer(String answer){
        boolean correct=false;
        if(getAnswer().equals(answer))
            correct=true;
        return correct;
    }
}
