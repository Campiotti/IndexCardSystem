package view;

import ics.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import persistence.Validifier;

public class Creator {

    @FXML
    public TextField creatorCDNameTxt,creatorCDPassPTxt,creatorCDCardsPRTxt;
    @FXML
    public Button creatorCDAddBtn, creatorSysBtn;

    private static Validifier validifier = new Validifier();

    public void creatorSysBtnA(ActionEvent actionEvent) {
        ScreenController.getInstance().activate("mainMenu");
    }

    public void creatorCDAddBtnA(ActionEvent actionEvent) {
        creatorCDNameTxt.getText();
        creatorCDPassPTxt.getText();
        creatorCDCardsPRTxt.getText();
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
    //TODO fix nullpointer which is occuring for some reason (prob just do if validifier == null ...
    private void validifyInputs(){
        if(validifier==null)
            validifier = new Validifier();
        if(validifier.checkName(creatorCDNameTxt.getText(),1,16) && validifier.checkNumber(creatorCDCardsPRTxt.getText()) && validifier.checkNumber(creatorCDPassPTxt.getText()))
            creatorCDAddBtn.setDisable(false);
    }
}
