package persistence;

import helper.ErrorLogger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConnectionManagerTest {

    @Test
    public void getConnection() {
        try {
            Connection con = ConnectionManager.getConnection();
            assertFalse(con.isClosed());
        } catch (IOException | SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

}