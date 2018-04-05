package controller;

import helper.ErrorLogger;
import model.CardDeck;
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
            new IndexCard(1,question,answer,isNumberQuestion).save();

        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    public void createCardDeck(String title, int passPercent, int cardsPerRun){
        try {
            CardDeck cardDeck = new CardDeck(title, passPercent, cardsPerRun);
            cardDeck.save();
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

}
