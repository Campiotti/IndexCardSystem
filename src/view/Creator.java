package view;

import controller.CreatorController;
import ics.ScreenController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.CardDeck;
import model.EasyAccess;
import persistence.Validifier;

import java.util.List;

public class Creator extends BaseView implements IView{

    @FXML
    public TextField creatorCDNameTxt,creatorCDPassPTxt,creatorCDCardsPRTxt, QAaddAtxt, QAaddQtxt;
    @FXML
    public Button creatorCDAddBtn, creatorSysBtn, QAaddQBtn, CDEditBtn, CDUpdateBtn, CDDeleteBtn;
    @FXML
    public AnchorPane QAAnchorTL, mainAnchor;
    @FXML
    public CheckBox QAaddMCB;
    @FXML
    public TableView CDTableView;
    @FXML
    protected TableColumn CDTCName, CDTCQ, CDTCPass, CDTCCPR;
    @FXML
    public ComboBox QACB;
    @FXML
    public TabPane tabPane;

    private static Validifier validifier = new Validifier();


    @FXML
    public void initialize(){
        //mainAnchor.widthProperty().addListener((obs, oldVal, newVal) -> System.out.println(mainAnchor.getWidth()+" , "+mainAnchor.getHeight()));
        //mainAnchor.heightProperty().addListener((obs, oldVal, newVal) -> System.out.println(mainAnchor.getWidth()+" , "+mainAnchor.getHeight()));
        //System.out.println(mainAnchor.getWidth()+" , "+mainAnchor.getHeight());
        QACB.valueProperty().addListener((obs,newVal,oldVal)->{
            validateQA();
        });


    }

    public void creatorSysBtnA(ActionEvent actionEvent) {
        ScreenController.getInstance().activate("mainMenu");
    }

    public void creatorCDAddBtnA(ActionEvent actionEvent) {
        int pp = Integer.parseInt(creatorCDPassPTxt.getText());
        int cpr = Integer.parseInt(creatorCDCardsPRTxt.getText());
        new CreatorController(this).createCardDeck(creatorCDNameTxt.getText(),pp,cpr);
        updateCDTable();
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
        //System.out.println("validify");
        if(validifier==null)
            validifier = new Validifier();
       // System.out.println(validifier.checkName(creatorCDNameTxt.getText(),1,24));
       // System.out.println(validifier.checkNumber(creatorCDPassPTxt.getText()));
       // System.out.println(validifier.checkNumber(creatorCDCardsPRTxt.getText()));
        if(validifier.checkName(creatorCDNameTxt.getText(),1,16) && validifier.checkNumber(creatorCDCardsPRTxt.getText()) && validifier.checkNumber(creatorCDPassPTxt.getText()))
            creatorCDAddBtn.disableProperty().set(false);
    }

    public void QAaddQTxtKT(KeyEvent keyEvent) {
    }

    public void QAaddAtxtKT(KeyEvent keyEvent) {
        validateQA();
    }

    public void QAaddMCBA(ActionEvent actionEvent) {
        validateQA();
    }

    public void QAaddQBtnA(ActionEvent actionEvent) {
        new CreatorController(this).addCard(QAaddQtxt.getText(),QAaddAtxt.getText(),QAaddMCB.isSelected());
    }

    private void validateQA(){
        boolean disbableBtn=true;
        if(quickMaths() && QACB.valueProperty().get()!=null)
            disbableBtn=false;
        QAaddQBtn.disableProperty().set(disbableBtn);

    }

    private boolean quickMaths(){
        if(QAaddMCB.isSelected()){
            try{
                Integer.parseInt(QAaddAtxt.getText());
                return false;
            }catch (Exception ignored){
                return true;
            }
        }else{
            return false;
        }
    }
    private void updateCDTable(){
        ObservableList<CardDeck> data = FXCollections.observableArrayList();
        CDTableView.getItems().clear();
        CDTableView.getSelectionModel().clearSelection();
        List<CardDeck> cardDecks = new EasyAccess().getAllCards();
        data.addAll(cardDecks);
        CDTCName.setCellValueFactory(
                new PropertyValueFactory<CardDeck,String>("title")
        );
        CDTCQ.setCellValueFactory(
                new PropertyValueFactory<EasyAccess,Integer>("cardCount")
        );
        CDTCPass.setCellValueFactory(
                new PropertyValueFactory<CardDeck,Integer>("passPercent")
        );
        CDTCCPR.setCellValueFactory(
                new PropertyValueFactory<CardDeck,Integer>("cardsPerRun")
        );
        CDTableView.setItems(data);


    }

    public void CDEditBtnA(ActionEvent actionEvent) {
        updateCDTable();
    }

    public void CDUpdateBtnA(ActionEvent actionEvent) {
        updateCDTable();
    }

    public void CDDeleteBtnA(ActionEvent actionEvent) {
        updateCDTable();
    }
}
