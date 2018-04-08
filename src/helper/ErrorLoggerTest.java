package helper;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ErrorLoggerTest {

    @Test
    public void log() {
        ErrorLogger.getInstance().log("test");
        try {
            InputStream fileInputStream = new FileInputStream("ErrorLog.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            assertEquals("test",bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}