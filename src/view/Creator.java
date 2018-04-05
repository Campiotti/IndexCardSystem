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
        mainAnchor.widthProperty().addListener((obs, oldVal, newVal) -> System.out.println(mainAnchor.getWidth()+" , "+mainAnchor.getHeight()));
        mainAnchor.heightProperty().addListener((obs, oldVal, newVal) -> System.out.println(mainAnchor.getWidth()+" , "+mainAnchor.getHeight()));
        //System.out.println(mainAnchor.getWidth()+" , "+mainAnchor.getHeight());
    }

    public void creatorSysBtnA(ActionEvent actionEvent) {
        ScreenController.getInstance().activate("mainMenu");
    }

    public void creatorCDAddBtnA(ActionEvent actionEvent) {

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
        if(validifier==null)
            validifier = new Validifier();
        if(validifier.checkName(creatorCDNameTxt.getText(),1,16) && validifier.checkNumber(creatorCDCardsPRTxt.getText()) && validifier.checkNumber(creatorCDPassPTxt.getText()))
            creatorCDAddBtn.setDisable(false);
    }

    private void resize(int tab, Double newVal){
        switch (tab){
            case 1:
                
                break;
            case 2:

                break;
            case 3:

                break;
        }
    }

    public void QAaddQTxtKT(KeyEvent keyEvent) {
    }

    public void QAaddAtxtKT(KeyEvent keyEvent) {
    }

    public void QAaddMCBA(ActionEvent actionEvent) {
        if(QAaddMCB.isSelected()){
            try{
                Integer.parseInt(QAaddAtxt.getText());
                QAaddQBtn.disableProperty().set(false);
            }catch (Exception ignored){
                QAaddQBtn.setDisable(true);
            }
        }else{
            QAaddQBtn.disableProperty().set(false);
        }
    }

    public void QAaddQBtnA(ActionEvent actionEvent) {
    }
}
