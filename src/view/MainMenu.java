package view;

import ics.Main;
import ics.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MainMenu {

    @FXML
    public Button mainMenuCreatorBtn,mainMenuPlayBtn,mainMenuCloseBtn;
    @FXML
    public AnchorPane mainMenuPane;
    @FXML
    public ImageView mainMenuImage;
    @FXML
    public GridPane mainMenuGridPane;


    @FXML
    public void initialize(){
        mainMenuPane.widthProperty().addListener((obs, oldVal, newVal) ->{
            resize(true,newVal);
        });

        mainMenuPane.heightProperty().addListener((obs, oldVal, newVal) ->{
            resize(false,newVal);
        });
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

    private void resize(boolean isWidth, Number newVal){
        if(isWidth){

        }else{

        }
    }
}
