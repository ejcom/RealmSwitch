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
        primaryStage.setTitle("RealmList manager by EJcom");
        primaryStage.setScene(new Scene(root, 374, 88));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                boolean saveIsOK = SaveLoadFile.save();
                if (saveIsOK) {
                    SaveLoadFile.save();
                }
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }


}
