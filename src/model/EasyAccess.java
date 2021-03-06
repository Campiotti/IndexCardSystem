package model;

import helper.ErrorLogger;
import persistence.ConnectionManager;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EasyAccess {

    public List<CardDeck> getAllCardDecks(){
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
                indexCard.setIsNumberQuestion(rs.getBoolean(5));
                list.add(indexCard);
            }
        } catch (SQLException | IOException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
        return list;
    }

    public Integer getCardDeckIdByTitle(String title){
        try {
            String sql="SELECT ID FROM CARDDECK WHERE TITLE='"+title+"'";
            Statement stmt = ConnectionManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException | IOException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
        return 0;
    }

    public int getIndexCardIdByQuestion(String question){
        try {
            String sql="SELECT ID FROM INDEXCARD WHERE QUESTION='"+question+"'";
            Statement stmt = ConnectionManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException | IOException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
        return 0;
    }
}
