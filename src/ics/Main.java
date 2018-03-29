package ics;

import controller.IController;
import helper.ErrorLogger;
import helper.Factory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    IController mainController = new Factory().getController("MainController");
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        primaryStage.setTitle("CICS - Campiotti's Index Card System");
        Scene scene = new Scene(root, 1280, 1024);
        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(1024);
        primaryStage.setMaxWidth(1920);
        primaryStage.setMaxHeight(1080);
        primaryStage.setResizable(true); //Anforderungen_erlfuellt ++;
        primaryStage.setScene(scene);
        primaryStage.setWidth(1280);
        primaryStage.setHeight(1024);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> onClose());
        ErrorLogger.log("--- CICS started ---","info.txt");
        ErrorLogger.log("w: "+primaryStage.getWidth(),"info.txt");
        ErrorLogger.log("h: "+primaryStage.getHeight(),"info.txt");
    }

    private void onClose(){
        ErrorLogger.log("--- Closing CICS ---","info.txt");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
