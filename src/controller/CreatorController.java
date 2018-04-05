package controller;

import helper.ErrorLogger;
import model.IndexCard;
import view.Creator;

import java.sql.SQLException;

public class CreatorController extends BaseController implements IController {
    private Creator creator;
    public CreatorController(Creator creator){
        this.creator=creator;
    }
    public void addCard(String question, String answer, boolean isNumberQuestion){
        try {
            new IndexCard(0,question,answer,isNumberQuestion);


        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

}
