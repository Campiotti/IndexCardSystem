package helper;

import controller.IController;
import controller.MainController;
import controller.MainMenuController;
import model.Answer;
import model.QandA;
import model.Question;

public class Factory {

    public IController getController(String type){
        switch (type.toLowerCase()){
            case"main":
                return new MainController();
            case"mainmenu":
                return new MainMenuController();
            default:
                return null;
        }
    }

    public QandA getQorA(String type){
        switch (type.toLowerCase()){
            case"answer":
                return new Answer();
            case"question":
                return new Question();
            default:
                return null;
        }
    }
}
