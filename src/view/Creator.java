package view;

import controller.CreatorController;
import ics.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import persistence.Validifier;

public class Creator extends BaseView implements IView{

    @FXML
    public TextField creatorCDNameTxt,creatorCDPassPTxt,creatorCDCardsPRTxt, QAaddAtxt, QAaddQtxt;
    @FXML
    public Button creatorCDAddBtn, creatorSysBtn, QAaddQBtn;
    @FXML
    public AnchorPane QAAnchorTL, mainAnchor;
    @FXML
    public CheckBox QAaddMCB;

    private static Validifier validifier = new Validifier();


    @FXML
    public void initialize(){
        //mainAnchor.widthProperty().addListener((obs, oldVal, newVal) -> System.out.println(mainAnchor.getWidth()+" , "+mainAnchor.getHeight()));
        //mainAnchor.heightProperty().addListener((obs, oldVal, newVal) -> System.out.println(mainAnchor.getWidth()+" , "+mainAnchor.getHeight()));
        //System.out.println(mainAnchor.getWidth()+" , "+mainAnchor.getHeight());
    }

    public void creatorSysBtnA(ActionEvent actionEvent) {
        ScreenController.getInstance().activate("mainMenu");
    }

    public void creatorCDAddBtnA(ActionEvent actionEvent) {
        int pp = Integer.parseInt(creatorCDPassPTxt.getText());
        int cpr = Integer.parseInt(creatorCDCardsPRTxt.getText());
        new CreatorController(this).createCardDeck(creatorCDNameTxt.getText(),pp,cpr);
    }

    public void creatorCDNameTxtKR(KeyEvent keyEvent) {
        validifyInputs();
    }

    public void creatorCDPassPTxtKR(KeyEvent keyEvent) {
        validifyInputs();
    }

    public void creatorCDCardsPRTxt(KeyEvent keyEvent) {
        validifyInputs();
    }

    private void validifyInputs(){
        System.out.println("validify");
        if(validifier==null)
            validifier = new Validifier();
        System.out.println(validifier.checkName(creatorCDNameTxt.getText(),1,24));
        System.out.println(validifier.checkNumber(creatorCDPassPTxt.getText()));
        System.out.println(validifier.checkNumber(creatorCDCardsPRTxt.getText()));
        if(validifier.checkName(creatorCDNameTxt.getText(),1,16) && validifier.checkNumber(creatorCDCardsPRTxt.getText()) && validifier.checkNumber(creatorCDPassPTxt.getText()))
            creatorCDAddBtn.disableProperty().set(false);
    }

    public void QAaddQTxtKT(KeyEvent keyEvent) {
    }

    public void QAaddAtxtKT(KeyEvent keyEvent) {
        quickMaths();
    }

    public void QAaddMCBA(ActionEvent actionEvent) {
        quickMaths();
    }

    public void QAaddQBtnA(ActionEvent actionEvent) {
        new CreatorController(this).addCard(QAaddQtxt.getText(),QAaddAtxt.getText(),QAaddMCB.isSelected());
    }

    private void quickMaths(){
        if(QAaddMCB.isSelected()){
            try{
                Integer.parseInt(QAaddAtxt.getText());
                QAaddQBtn.disableProperty().set(false);
            }catch (Exception ignored){
                QAaddQBtn.disableProperty().set(true);
            }
        }else{
            QAaddQBtn.disableProperty().set(false);
        }
    }
}
