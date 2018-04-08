package model;

import helper.ErrorLogger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.DBManager;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * WARNING: RUNNING ANY OF THESE TESTS WILL CLEAR CONTENTS OF WHOLE DATABASE!
 */
public class EasyAccessTest {

    private final String deck = "testA";
    private final String card = "testB";
    private EasyAccess easyAccess;
    @Before
    public void setUp() throws Exception {
        DBManager.getInstance().clearDatabase();
        CardDeck cardDeck = new CardDeck();
        cardDeck.setTitle(deck);
        cardDeck.save();
        IndexCard indexCard = new IndexCard();
        indexCard.setCardDeckFk(1);
        indexCard.setQuestion(card);
        indexCard.save();
        this.easyAccess = new EasyAccess();
    }

    @After
    public void tearDown() throws Exception {
        CardDeck cardDeck = new CardDeck();
        cardDeck.id.set(""+easyAccess.getCardDeckIdByTitle(deck));
        cardDeck.delete();
        IndexCard indexCard = new IndexCard();
        indexCard.id.set(""+easyAccess.getIndexCardIdByQuestion(card));
        indexCard.delete();
    }

    @Test
    public void getAllCardDecks() {
        assertEquals(1,easyAccess.getAllCardDecks().size());
    }

    @Test
    public void getAllIndexCards() {
        assertEquals(1,easyAccess.getAllIndexCards().size());
    }

    @Test
    public void getCardDeckIdByTitle() {
        try {
            CardDeck cardDeck = new CardDeck();
            cardDeck.id.set(""+easyAccess.getCardDeckIdByTitle(deck));
            cardDeck.view();
            assertEquals(deck,cardDeck.getTitle());
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }


    }

    @Test
    public void getIndexCardIdByQuestion() {
        try{
            IndexCard indexCard = new IndexCard();
            indexCard.id.set(""+easyAccess.getIndexCardIdByQuestion(card));
            indexCard.view();
            assertEquals(card,indexCard.getQuestion());
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }
}