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
public class IndexCardTest {
    private final String deck = "testA";
    private final String card = "testB";
    private final String cardAnswer="answer";
    @Before
    public void setUp() throws Exception {
        try {
            DBManager.getInstance().clearDatabase();
            CardDeck cardDeck = new CardDeck();
            cardDeck.setTitle(deck);
            cardDeck.save();
            IndexCard indexCard = new IndexCard();
            indexCard.setCardDeckFk(1);
            indexCard.setQuestion(card);
            indexCard.setAnswer(cardAnswer);
            indexCard.save();
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
        CardDeck cardDeck = new CardDeck();
        cardDeck.id.set(""+1);
        cardDeck.delete();
        IndexCard indexCard = new IndexCard();
        indexCard.id.set(""+1);
        indexCard.delete();
    }

    @Test
    public void checkAnswer() {
        try {
            IndexCard indexCard = new IndexCard();
            indexCard.id.set("1");
            indexCard.view();
            assertTrue(indexCard.checkAnswer(cardAnswer));
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    @Test
    public void getCardDeckName() {
        try {
            IndexCard indexCard = new IndexCard();
            indexCard.id.set("1");
            indexCard.view();
            assertEquals(deck,indexCard.getCardDeckName());
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }
}