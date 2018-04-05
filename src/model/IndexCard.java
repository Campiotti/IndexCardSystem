package model;

import helper.ErrorLogger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IndexCard implements IEntity{

    private Question question;
    private List<Answer> answerList;

    public IndexCard() throws SQLException {
        this.question = new Question();
        this.answerList = new ArrayList<>();
    }

    public IndexCard(Question question){
        this.question = question;
        this.answerList = new ArrayList<>();
    }

    public IndexCard(Question question, Answer answer){
        this.question=question;
        this.answerList = new ArrayList<>();
        this.answerList.add(answer);
    }

    public IndexCard(Question question, List<Answer> answers){
        this.question=question;
        this.answerList = answers;
    }


    public void setQuestion(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public void addAnswer(Answer answer){
        this.answerList.add(answer);
    }

    public void removeAnswer(Answer answer){
        this.answerList.remove(answer);
    }


    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void view(int id) {
        try {
            this.question=new Question();
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
        this.question.view(id);
        //TODO get all answers by question id and add them to the answers list
    }

    @Override
    public void edit() {

    }
}
