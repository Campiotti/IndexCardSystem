package model;

import helper.ErrorLogger;
import org.h2.index.Index;
import persistence.ConnectionManager;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EasyAccess {

    public List<CardDeck> getAllCards(){
        List<CardDeck> list = new ArrayList<>();

        try {
            String sql = "select * from CardDeck";
            Statement stmt = ConnectionManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                CardDeck cardDeck = new CardDeck();
                cardDeck.id.setValue(String.valueOf(rs.getInt(1)));
                cardDeck.setTitle((rs.getString(2)));
                cardDeck.setPassPercent(rs.getInt(3));
                cardDeck.setCardsPerRun(rs.getInt(4));
                list.add(cardDeck);
            }
        } catch (SQLException | IOException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
        return list;
    }
    public List<IndexCard> getAllIndexCards(){
        List<IndexCard> list = new ArrayList<>();

        try {
            String sql = "select * from IndexCard";
            Statement stmt = ConnectionManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                IndexCard indexCard = new IndexCard();
                indexCard.id.setValue(String.valueOf(rs.getInt(1)));
                indexCard.setCardDeckFk((rs.getInt(2)));
                indexCard.setQuestion(rs.getString(3));
                indexCard.setAnswer(rs.getString(4));
                indexCard.setNumberQuestion(rs.getBoolean(5));
                list.add(indexCard);
            }
        } catch (SQLException | IOException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
        return list;
    }

}
