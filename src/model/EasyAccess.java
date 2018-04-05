package model;

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
            e.printStackTrace();
        }
        return list;
    }
}
