package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        if (WoW.loadWowDirectory()) {
            primaryStage.setTitle(WoW.getWowDirectory());
        } else {
            primaryStage.setTitle("Press Right Click on Apply");
        }

        primaryStage.setScene(new Scene(root, 374, 88));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                DB.saveDB();
                WoW.saveWowDirectory();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }


}
