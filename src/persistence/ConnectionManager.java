package persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionManager {
    private static final String FILE = "/db.properties";
    private static Connection con;

    private ConnectionManager() {}

    /**
     * Creates the connection between the database.
     * The connection properties url,username, password are found
     * in the file /db.properties.
     * @return a connection to the database.
     * @throws IOException /db.properties could not be read.
     * @throws SQLException error at the database access.
     */
    public static Connection getConnection() throws IOException, SQLException {
        if (con == null) {
            Properties prop = loadDbParam();
            con = DriverManager.getConnection(prop.getProperty("url"),
                    prop.getProperty("user", ""),
                    prop.getProperty("password", ""));
        }
        return con;

    }

    /**
     * Closes the connection to the database.
     * @throws Exception error which should never happen.
     */
    @SuppressWarnings("RedundantThrows")
    public static void closeConnection() throws Exception{
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (Exception ignored) {
        }
    }
    /**
     * Opens file (String)FILE value and reads the database properties.
     * @return Database properties.
     * @throws IOException in case file is not at path or another error occurs.
     */

    private static Properties loadDbParam() throws IOException {
        try (InputStream in = ConnectionManager.class.getResourceAsStream(FILE)) {
            Properties prop = new Properties();
            if (in != null)
                prop.load(in);
            return prop;
        }
    }

}
