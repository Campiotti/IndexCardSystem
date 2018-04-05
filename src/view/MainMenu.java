package view;

import ics.Main;
import ics.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class MainMenu extends BaseView implements IView{

    @FXML
    public Button mainMenuCreatorBtn,mainMenuPlayBtn,mainMenuCloseBtn; //16px 75w 37h | 20px 64w 44h | 16px 61w 37h
    @FXML
    public AnchorPane mainMenuPane; // 600w 400h
    @FXML
    public ImageView mainMenuImage; //325w 132h
    @FXML
    public GridPane mainMenuGridPane;


    @FXML
    public void initialize(){
        mainMenuPane.widthProperty().addListener((obs, oldVal, newVal) ->{
            mainMenuImage.setFitWidth((Double)newVal*0.54166);
            mainMenuCreatorBtn.setPrefWidth((Double)newVal*0.125);
            mainMenuPlayBtn.setPrefWidth((Double)newVal*0.10666);
            mainMenuCloseBtn.setPrefWidth((Double)newVal*0.101666);
        });

        mainMenuPane.heightProperty().addListener((obs, oldVal, newVal) ->{
            mainMenuImage.setFitHeight((Double)newVal*0.33);
            mainMenuCreatorBtn.setPrefHeight((Double)newVal*0.0925);
            mainMenuCreatorBtn.setFont(Font.font("System",(Double)newVal*0.04*0.75));
            mainMenuPlayBtn.setPrefHeight((Double)newVal*0.11);
            mainMenuPlayBtn.setFont(Font.font("System",(Double)newVal*0.05*0.75));
            mainMenuCloseBtn.setPrefHeight((Double)newVal*0.0925);
            mainMenuCloseBtn.setFont(Font.font("System",(Double)newVal*0.04*0.75));
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
