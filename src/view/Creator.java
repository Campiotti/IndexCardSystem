package view;

import controller.CreatorController;
import helper.ErrorLogger;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//TODO add 2 buttons (1 each) for Question and IndexCard that can cancel editing (or force allow save button on edit btn clicked) - Also save or update depending on if the lbl is set with txt n stuff.
@SuppressWarnings("unchecked")
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

    public Label CDLbl,QALbl;

    private static Validifier validifier = new Validifier();

    @FXML
    public void initialize(){
        //mainAnchor.widthProperty().addListener((obs, oldVal, newVal) -> System.out.println(mainAnchor.getWidth()+" , "+mainAnchor.getHeight()));
        //mainAnchor.heightProperty().addListener((obs, oldVal, newVal) -> System.out.println(mainAnchor.getWidth()+" , "+mainAnchor.getHeight()));
        //System.out.println(mainAnchor.getWidth()+" , "+mainAnchor.getHeight());
        QACB.valueProperty().addListener((obs,newVal,oldVal)-> validateQA());

        CDCB.valueProperty().addListener((obs,newVal,oldVal)->{
            CDEditBtn.disableProperty().set(false);
            CDDeleteBtn.disableProperty().set(false);
        });

        QACB2.valueProperty().addListener((obs,newVal,oldVal)->{
            QAEditBtn.disableProperty().set(false);
            QADeleteBtn.disableProperty().set(false);
        });
        updateTablesAndComboBoxes();

    }

    private void updateTablesAndComboBoxes(){
        updateQATable();
        updateCDTable();
        updateCDComboBox();
        updateQAComboBox();
        updateQAComboBox2();
        disableButtons();
    }
    private void disableButtons(){
        CDDeleteBtn.setDisable(true);
        CDEditBtn.setDisable(true);
        QADeleteBtn.setDisable(true);
        QAEditBtn.setDisable(true);
        QAaddQBtn.setDisable(true);
        creatorCDAddBtn.setDisable(true);
    }

    public void creatorSysBtnA(ActionEvent actionEvent) {
        ScreenController.getInstance().activate("mainMenu");
    }

    public void creatorCDAddBtnA(ActionEvent actionEvent) {
        String id = CDLbl.getText();
        String title = creatorCDNameTxt.getText();
        int pp = Integer.parseInt(creatorCDPassPTxt.getText());
        int cpr = Integer.parseInt(creatorCDCardsPRTxt.getText());
        System.out.println(CDLbl.getText());
        if(CDLbl.getText().equals("") || CDLbl.getText()==null){
            new CreatorController(this).createCardDeck(title,pp,cpr);
        }else{
            try {
                CardDeck cardDeck = new CardDeck(title,pp,cpr);
                cardDeck.id.set(id);
                cardDeck.update();
            } catch (SQLException e) {
                ErrorLogger.getInstance().log(e.getLocalizedMessage());
            }
        }
        clearTxtInput(true);
        updateTablesAndComboBoxes();
    }

    private void clearTxtInput(boolean Cd){
        if(Cd){
            creatorCDNameTxt.setText("");
            creatorCDPassPTxt.setText("");
            creatorCDCardsPRTxt.setText("");
            CDLbl.setText("");
        }else{
            QAaddQtxt.setText("");
            QAaddAtxt.setText("");
            QALbl.setText("");
            QAaddMCB.selectedProperty().set(false);
            QACB.getSelectionModel().clearSelection();
        }
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
        if(validifier.checkName(creatorCDNameTxt.getText(),1,24) && validifier.checkNumber(creatorCDCardsPRTxt.getText()) && validifier.checkNumber(creatorCDPassPTxt.getText()))
            creatorCDAddBtn.disableProperty().set(false);
        else
            creatorCDAddBtn.disableProperty().set(true);
    }
    private void validifyQuestion(){
        if(validifier==null)
            validifier = new Validifier();
        if((QAaddMCB.isSelected() && validifier.checkNumber(QAaddAtxt.getText())) && QAaddQtxt.getText().length()>0)
            QAaddQBtn.disableProperty().set(false);
        else
            QAaddQBtn.disableProperty().set(true);
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
        if(QALbl.getText()==null || QALbl.getText().equals("")){
            new CreatorController(this).addCard(QAaddQtxt.getText(),QAaddAtxt.getText(),QAaddMCB.isSelected());
        }else{
            try {
                IndexCard indexCard = new IndexCard(new EasyAccess().getCardDeckIdByTitle(QACB.getSelectionModel().getSelectedItem().toString()),QAaddQtxt.getText(),QAaddAtxt.getText(),QAaddMCB.isSelected());
                indexCard.id.set(QALbl.getText());
                indexCard.update();
            } catch (SQLException e) {
                ErrorLogger.getInstance().log(e.getLocalizedMessage());
            }
        }
        clearTxtInput(false);
        updateTablesAndComboBoxes();


    }

    private void validateQA(){
        boolean disable=true;
        if(quickMaths() && QACB.valueProperty().get()!=null)
            disable=false;
        QAaddQBtn.disableProperty().set(disable);

    }

    private boolean quickMaths(){
        if(QAaddMCB.isSelected()){
            try{
                 int a = Integer.parseInt(QAaddAtxt.getText());
                return true;
            }catch (Exception ignored){
                return false;
            }
        }else{
            return true;
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
        QATCQ.setCellValueFactory(
                new PropertyValueFactory<IndexCard,String>("question")
        );
        QATCA.setCellValueFactory(
                new PropertyValueFactory<IndexCard,String>("answer")
        );
        QATCM.setCellValueFactory(
                new PropertyValueFactory<IndexCard,Boolean>("isNumberQuestion")
        );
        QATCCD.setCellValueFactory(
                new PropertyValueFactory<IndexCard,String>("cardDeckName")
        );
        QATableView.setItems(data);
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
        readIntoCDtxts();
        updateTablesAndComboBoxes();
    }

    public void CDDeleteBtnA(ActionEvent actionEvent) {
        try {
            CardDeck cardDeck = new CardDeck();
            cardDeck.id.set(""+new EasyAccess().getCardDeckIdByTitle(CDCB.getSelectionModel().getSelectedItem().toString()));
            cardDeck.delete();
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
        updateTablesAndComboBoxes();
    }

    public void QAEditBtnA(ActionEvent actionEvent) {
        readIntoQAtxts();
        updateTablesAndComboBoxes();
    }

    public void QADeleteBtnA(ActionEvent actionEvent) {
        try {
            IndexCard indexCard = new IndexCard();
            indexCard.id.set(""+new EasyAccess().getIndexCardIdByQuestion(QACB2.getSelectionModel().getSelectedItem().toString()));
            indexCard.delete();
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
        updateTablesAndComboBoxes();
    }

    private void readIntoCDtxts(){
        try {
            CardDeck cardDeck = new CardDeck();
            cardDeck.id.set(""+new EasyAccess().getCardDeckIdByTitle(CDCB.getSelectionModel().getSelectedItem().toString()));
            //System.out.println(cardDeck.id.get());
            cardDeck.view();
            CDLbl.setText(""+cardDeck.id.get());
            creatorCDNameTxt.setText(""+cardDeck.getTitle());
            creatorCDCardsPRTxt.setText(""+cardDeck.getCardsPerRun());
            creatorCDPassPTxt.setText(""+cardDeck.getPassPercent());
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }

    private void readIntoQAtxts(){
        try {
            IndexCard indexCard = new IndexCard();
            indexCard.id.set(""+new EasyAccess().getIndexCardIdByQuestion(QACB2.getSelectionModel().getSelectedItem().toString()));
            //System.out.println(indexCard.id.get());
            indexCard.view();
            QALbl.setText(indexCard.id.get());
            QAaddQtxt.setText(indexCard.getQuestion());
            QAaddAtxt.setText(indexCard.getAnswer());
            List<CardDeck> cardDecks = new EasyAccess().getAllCardDecks();
            for (int i = 0; i < cardDecks.size(); i++) {
                System.out.println(cardDecks.get(i).getTitle()+" -- "+indexCard.getCardDeckName());
                if(cardDecks.get(i).getTitle().equals(indexCard.getCardDeckName())){
                    QACB.getSelectionModel().select(i);
                    break;
                }
            }
            QAaddMCB.selectedProperty().set(indexCard.getIsNumberQuestion());
        } catch (SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }
}
