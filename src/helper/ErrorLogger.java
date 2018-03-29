package helper;

import java.io.*;

public class ErrorLogger {

    private static final String defaultFile="ErrorLog.txt";

    public static void log(String exception){
        new ErrorLogger().logInternal(exception, defaultFile);
    }

    public static void log(String exception, String customFile){
        new ErrorLogger().logInternal(exception, customFile);
    }

    private void logInternal(String exception, String file){
        try {
            createFileIfNotExists(file);
            File f = new File(file);
            FileOutputStream outputStream = new FileOutputStream(f);
            byte content[] = exception.getBytes();
            outputStream.write(content);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFileIfNotExists(String fileName){
        File file = new File(fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ignored) {}
        }

    }

}
