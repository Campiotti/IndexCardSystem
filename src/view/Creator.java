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
import model.IndexCard;
import persistence.Validifier;

import java.util.ArrayList;
import java.util.List;

public class Creator extends BaseView implements IView{

    @FXML
    public TextField creatorCDNameTxt,creatorCDPassPTxt,creatorCDCardsPRTxt, QAaddAtxt, QAaddQtxt;
    @FXML
    public Button creatorCDAddBtn, creatorSysBtn, QAaddQBtn, CDEditBtn, CDDeleteBtn, QAEditBtn,QADeleteBtn;
    @FXML
    public AnchorPane QAAnchorTL, mainAnchor;
    @FXML
    public CheckBox QAaddMCB;
    @FXML
    public TableView CDTableView,QATableView;
    @FXML
    protected TableColumn CDTCName, CDTCQ, CDTCPass, CDTCCPR, QATCQ, QATCA, QATCM, QATCCD;
    @FXML
    public ComboBox QACB,CDCB,QACB2;
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

        CDCB.valueProperty().addListener((obs,newVal,oldVal)->{

        });

        QACB2.valueProperty().addListener((obs,newVal,oldVal)->{

        });
        updateCDTable();
        updateQATable();
        updateCDComboBox();
        updateQAComboBox();
        updateQAComboBox2();

    }

    private void initCDTable(){
        /*CDTableView.setRowFactory( tv ->{
            TableRow<CardDeck> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    CardDeck clickedRow = row.getItem();
                }
            });
            return row ;
        });*/
    }

    private void initQATable(){
        /*QATableView.setRowFactory( tv ->{
            TableRow<IndexCard> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    IndexCard clickedRow = row.getItem();
                }
            });
            return row ;
        });*/
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
        List<CardDeck> cardDecks = new EasyAccess().getAllCardDecks();
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

    private void updateQATable(){
        ObservableList<IndexCard> data = FXCollections.observableArrayList();
        QATableView.getItems().clear();
        QATableView.getSelectionModel().clearSelection();
        List<IndexCard> indexCards = new EasyAccess().getAllIndexCards();
        data.addAll(indexCards);
        QATCA.setCellValueFactory(
                new PropertyValueFactory<IndexCard,String>("question")
        );
        QATCQ.setCellValueFactory(
                new PropertyValueFactory<IndexCard,String>("answer")
        );
        QATCM.setCellValueFactory(
                new PropertyValueFactory<IndexCard,Boolean>("isNumberQuestion")
        );
        QATCCD.setCellValueFactory(
                new PropertyValueFactory<IndexCard,String>("cardDeckName")
        );
    }

    private void updateCDComboBox(){
        CDCB.getItems().clear();
        CDCB.getSelectionModel().clearSelection();
        List<CardDeck> data = new ArrayList<>(new EasyAccess().getAllCardDecks());
        for(CardDeck cardDeck: data)
            CDCB.getItems().add(cardDeck.getTitle());
    }

    private void updateQAComboBox(){
        QACB.getItems().clear();
        QACB.getSelectionModel().clearSelection();
        List<CardDeck> data = new ArrayList<>(new EasyAccess().getAllCardDecks());
        for(CardDeck cardDeck: data)
            QACB.getItems().add(cardDeck.getTitle());
    }

    private void updateQAComboBox2(){
        QACB2.getItems().clear();
        QACB2.getSelectionModel().clearSelection();
        List<IndexCard> data = new ArrayList<>(new EasyAccess().getAllIndexCards());
        for(IndexCard indexCard : data)
            QACB2.getItems().add(indexCard.getQuestion());
    }

    public void CDEditBtnA(ActionEvent actionEvent) {
        updateCDTable();
    }

    public void CDDeleteBtnA(ActionEvent actionEvent) {
        updateCDTable();
    }

    public void QAEditBtnA(ActionEvent actionEvent) {
    }

    public void QADeleteBtnA(ActionEvent actionEvent) {
    }
}
