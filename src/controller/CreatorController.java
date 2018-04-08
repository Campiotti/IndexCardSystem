package controller;

import helper.ErrorLogger;
import helper.Factory;
import model.CardDeck;
import model.IEntity;
import model.IndexCard;
import view.Creator;

import java.sql.SQLException;

public class CreatorController extends BaseController implements IController {

    public CreatorController(){}

    public void addCard(String id, String question, String answer, boolean isNumberQuestion){
        try {
            IEntity model = new Factory().getModel(true);
            Object[] obj = new Object[]{id,question,answer,isNumberQuestion};
            new IndexCard(Integer.parseInt(id),question,answer,isNumberQuestion).save();

        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    public void createCardDeck(String title, int passPercent, int cardsPerRun){
        try {
            IEntity model = new Factory().getModel(false);
            Object[] obj = new Object[]{title,passPercent,cardsPerRun};
            model.patchData(obj,false);
            saveModel(model);
        } catch (Exception e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    public void updateCard(String id, String question, String answer, boolean isNumberQuestion){
        IEntity model = new Factory().getModel(true);
        Object[] obj = new Object[]{id,question,answer,isNumberQuestion};
        model.patchData(obj,true);
        updateModel(model);

    }

    public void updateCardDeck(String id, String title, int passPercent, int cardsPerRun){
        IEntity model = new Factory().getModel(false);
        Object[] obj = new Object[]{id,title,passPercent,cardsPerRun};
        model.patchData(obj,true);
        updateModel(model);
    }

    public void deleteCard(String id){
        IEntity model = new Factory().getModel(true);
        model.patchData(new Object[]{id},true);
        deleteModel(model);
    }
    public void deleteCardDeck(String id){
        IEntity model = new Factory().getModel(false);
        model.patchData(new Object[]{id},true);
        deleteModel(model);
    }
}
