package model;

import helper.ErrorLogger;
import org.junit.Before;
import org.junit.Test;
import persistence.DBManager;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * WARNING: RUNNING ANY OF THESE TESTS WILL CLEAR CONTENTS OF WHOLE DATABASE!
 */
public class CardDeckTest {
    private final String deck = "testA";
    private final String card = "testB";
    @Before
    public void Before(){
        try {
            DBManager.getInstance().clearDatabase();
            CardDeck cardDeck = new CardDeck();
            cardDeck.setTitle(deck);
            cardDeck.save();
            IndexCard indexCard = new IndexCard();
            indexCard.setCardDeckFk(1);
            indexCard.setQuestion(card);
            indexCard.save();
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }
    @Test
    public void view() {
        try {
            CardDeck cardDeck = new CardDeck();
            cardDeck.id.set(""+1);
            cardDeck.view();
            assertEquals(deck,cardDeck.getTitle());
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    @Test
    public void edit() {
        try {
            CardDeck cardDeck = new CardDeck();
            cardDeck.id.set(""+1);
            cardDeck.setTitle(card);
            cardDeck.edit();
            CardDeck c2 = new CardDeck();
            c2.id.set(""+1);
            c2.view();
            assertEquals(card,c2.getTitle());
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    @Test
    public void patchData() {
        try {
            CardDeck cardDeck = new CardDeck();
            cardDeck.patchData(new Object[]{255},true);
            assertEquals(""+255,cardDeck.id.get());
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    @Test
    public void getQuestionsCount() {
        try {
            CardDeck cardDeck = new CardDeck();
            cardDeck.id.set(""+1);
            cardDeck.view();
            assertEquals(1,cardDeck.getQuestionsCount());
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }
}