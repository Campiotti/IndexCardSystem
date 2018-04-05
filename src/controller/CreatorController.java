package controller;

import model.Answer;
import model.Question;
import view.Creator;

import java.sql.SQLException;

public class CreatorController extends BaseController implements IController {
    private Creator creator;
    public CreatorController(Creator creator){
        this.creator=creator;
    }
    public void addCard(String Question, String Answer, boolean isNumberQuestion){
        try {
            Question question = new Question();
            Answer answer = new Answer();
            answer.answer.set(Answer);
            answer.questionFk.set(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
