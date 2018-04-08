package helper;

import model.CardDeck;
import model.IEntity;
import model.IndexCard;

import java.sql.SQLException;


public class Factory {

    public IEntity getModel(boolean indexCard){
        try {
            if(indexCard)
                return  new IndexCard();
            else
                return new CardDeck();
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
        return null;
    }
}
