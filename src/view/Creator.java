package view;

import controller.CreatorController;
import helper.ErrorLogger;
import helper.Factory;
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
import model.IEntity;
import model.IndexCard;
import persistence.Validifier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        this.backToMainMenu();
    }

    public void creatorCDAddBtnA(ActionEvent actionEvent) {
        String id = CDLbl.getText();
        String title = creatorCDNameTxt.getText();
        int pp = Integer.parseInt(creatorCDPassPTxt.getText());
        int cpr = Integer.parseInt(creatorCDCardsPRTxt.getText());
        //System.out.println(CDLbl.getText());
        if(CDLbl.getText().equals("") || CDLbl.getText()==null)
            new CreatorController().createCardDeck(title,pp,cpr);
        else
            new CreatorController().updateCardDeck(id,title,pp,cpr);

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
        if(creatorCDNameTxt.getText().length()>0 && creatorCDNameTxt.getText().length()<25 && validifier.checkNumber(creatorCDCardsPRTxt.getText()) && validifier.checkNumber(creatorCDPassPTxt.getText())
                && Integer.parseInt(creatorCDPassPTxt.getText())>-1 && Integer.parseInt(creatorCDPassPTxt.getText())<101)
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
        validateQA();
    }

    public void QAaddAtxtKT(KeyEvent keyEvent) {
        validateQA();
    }

    public void QAaddMCBA(ActionEvent actionEvent) {
        validateQA();
    }

    public void QAaddQBtnA(ActionEvent actionEvent) {
        if(QALbl.getText()==null || QALbl.getText().equals("")){
            new CreatorController().addCard(new EasyAccess().getCardDeckIdByTitle(QACB.getSelectionModel().getSelectedItem().toString()).toString(),QAaddQtxt.getText(),QAaddAtxt.getText(),QAaddMCB.isSelected());
        }else{
            IEntity model = new Factory().getModel(true);
            Object[] obj = new Object[]{QALbl.getText(),new EasyAccess().getCardDeckIdByTitle(QACB.getSelectionModel().getSelectedItem().toString()),QAaddQtxt.getText(),QAaddAtxt.getText(),QAaddMCB.isSelected()};
            model.patchData(obj,true);
            model.edit();
        }
        clearTxtInput(false);
        updateTablesAndComboBoxes();


    }

    private void validateQA(){
        boolean disable=true;

        if(quickMaths() && validateQAtexts() && validateQAcheckBox())
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
    private boolean validateQAtexts(){
        return QAaddQtxt.getText().length() > 0 && QAaddQtxt.getText().length() < 25 && QAaddAtxt.getText().length() > 0 && QAaddAtxt.getText().length() < 25;
    }
    private boolean validateQAcheckBox(){
        try{return QACB.getSelectionModel().getSelectedItem().toString() != null && QACB.getSelectionModel().getSelectedIndex()>-1;}
        catch (Exception ignored){return false;}
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
        IEntity model = new Factory().getModel(false);
        model.patchData(new Object[]{new EasyAccess().getCardDeckIdByTitle(CDCB.getSelectionModel().getSelectedItem().toString())},true);
        model.delete();
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
                //System.out.println(cardDecks.get(i).getTitle()+" -- "+indexCard.getCardDeckName());
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
