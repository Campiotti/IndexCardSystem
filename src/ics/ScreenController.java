package ics;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

/**
 * Found on StackOverflow - https://stackoverflow.com/a/37276108
 * @author MrOerni
 */
public class ScreenController {
    private HashMap<String, Pane> screenMap = new HashMap<>();
    private Scene main;

    private static ScreenController instance;

    public static ScreenController getInstance(){
        if(instance==null)
            instance = new ScreenController();
        return instance;
    }

    public void setMain(Scene main){
        this.main=main;
    }

    protected void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    public void activate(String name){
        main.setRoot( screenMap.get(name) );
    }
}