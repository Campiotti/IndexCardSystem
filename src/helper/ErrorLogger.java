package helper;

import java.io.*;

/**
 * Logs errors and puts them into either the default error file or a custom called error file.
 * Optimized as to use as little static as possible but still be as usable as possible.
 */
public class ErrorLogger {
    /**
     * This is the default file which is logged into unless the overwritten method is called with a custom file name
     */
    private static final String defaultFile="ErrorLog.txt";
    /**
     * used to make sure that the default error file emptied when first written into, to clear out old logs.
     * -- only for defaultFile , custom files are not emptied upon first write into of program.
     */
    private static boolean emptied=false;

    /**
     * Calls the internal log method and writes the text it got into there.
     * @param text String text which is written into the file.
     */
    public static void log(String text){
        new ErrorLogger().logInternal(text, defaultFile);
    }

    /**
     * Calls the internal log method with a custom file name which is given as a parameter - overwritten method from original.
     * @param text String text which is to be written into the file.
     * @param customFile String name of the file which is to be written into.
     */
    public static void log(String text, String customFile){
        new ErrorLogger().logInternal(text, customFile);
    }

    /**
     * Writes the text given into the file given.
     * @param text String text which is to be written into the file.
     * @param file String name of the file which is to be written into.
     */
    private void logInternal(String text, String file){
        try {
            createFileIfNotExists(file);
            File f = new File(file);
            FileOutputStream outputStream;
            //append makes sure that it adds to the file instead of overwriting all of it's contents
            if(!emptied&&file.equals(defaultFile)){
                outputStream = new FileOutputStream(f, false);
                emptied=true;
            }else
                outputStream= new FileOutputStream(f,true);
            byte content[] = text.getBytes();
            outputStream.write(content);
            outputStream.write('\n');
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * helper method - creates a new file if a file by that name in that location does not yet exist.
     * (Also used to not have to bother with the try catch in the logInternal)
     * @param fileName String name of the file to be created if it does not yet exist.
     */
    private void createFileIfNotExists(String fileName){
        File file = new File(fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ignored) {}
        }

    }

}
