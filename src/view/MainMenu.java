package view;

import helper.ErrorLogger;
import ics.Main;
import ics.ScreenController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenu {

    @FXML
    public Button mainMenuCreatorBtn,mainMenuPlayBtn,mainMenuCloseBtn;
    @FXML
    public AnchorPane mainMenuPane;



    @FXML
    public void initialize(){
    }

    @FXML
    public void mainMenuPlayBtnA(ActionEvent actionEvent) {

    }

    @FXML
    public void mainMenuCloseBtnA(ActionEvent actionEvent) {
        Main.onClose();
        System.exit(-1);
    }

    @FXML
    public void mainMenuCreatorBtnA(ActionEvent actionEvent) {
        ScreenController.getInstance().activate("creator");
    }
}
