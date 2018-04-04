package view;

import ics.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Creator {

    @FXML
    public TextField creatorCDNameTxt,creatorCDPassPTxt,creatorCDCardsPRTxt;
    @FXML
    public Button creatorCDAddBtn, creatorSysBtn;

    public void creatorSysBtnA(ActionEvent actionEvent) {
        ScreenController.getInstance().activate("mainMenu");
    }
}
