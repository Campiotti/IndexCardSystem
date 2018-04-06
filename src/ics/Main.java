package ics;

import helper.ErrorLogger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import persistence.DBManager;
import view.MainMenu;

public class Main extends Application {
    //http://www.leepoint.net/GUI/structure/40mvc.html
    @Override
    public void start(Stage primaryStage) throws Exception{
/*        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/mainMenu.fxml"));
        MainMenu mainMenu = loader.getController();
        mainMenu.*/
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/mainMenu.fxml"));
        primaryStage.setTitle("CICS - Campiotti's Index Card System");
        Scene scene = new Scene(root, 1280, 1024);
        ScreenController.getInstance().setMain(scene);
        ScreenController.getInstance().addScreen("mainMenu",FXMLLoader.load(getClass().getResource( "../fxml/mainMenu.fxml")));
        ScreenController.getInstance().addScreen("creator",FXMLLoader.load(getClass().getResource( "../fxml/creator.fxml")));
        ScreenController.getInstance().activate("mainMenu");
        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(1024);
        primaryStage.setMaxWidth(1920);
        primaryStage.setMaxHeight(1080);
        primaryStage.setResizable(true); //Anforderungen_erlfuellt ++;
        primaryStage.setScene(scene);
        primaryStage.setWidth(1280);
        primaryStage.setHeight(1024);
        primaryStage.getIcons().add(new Image("/res/Schmitz'sFavourite.png"));
        primaryStage.show();
        DBManager.getInstance().seed();//runs db seed to make sure database is present if it's not yet existent.
        primaryStage.setOnCloseRequest(e -> onClose());
        ErrorLogger.getInstance().log("--- CICS started ---","info.txt");
        ErrorLogger.getInstance().log("w: "+primaryStage.getWidth(),"info.txt");
        ErrorLogger.getInstance().log("h: "+primaryStage.getHeight(),"info.txt");

    }

    public static void onClose(){
        ErrorLogger.getInstance().log("--- Closing CICS ---","info.txt");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
