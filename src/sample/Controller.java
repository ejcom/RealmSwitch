package sample;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.WritableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class Controller extends DB {
    private static int selectionItem;

    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private URL location;

    @FXML
    private Button applyButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button saveButton;

    @FXML
    private Label statusLabel;

    @FXML
    private ComboBox<String> realmListComboBox;

    @FXML
    void initialize() {
        DB.loadDB();
        String importRealmList = WoW.importRealmList();
        if (!importRealmList.equals("")) {
            boolean addIsOK = addRealmList(importRealmList);
            if (addIsOK) {
                reloadComboBox();
                statusLabel.setText("realmlist.wtf Imported");
            } else {
            }
        }

        realmListComboBox.setItems(FXCollections.observableArrayList(getRealmLists()));
        if (getRealmLists().size() == 0) {
            statusLabel.setText("RealmList not found");
        }

        saveButton.setOnAction(event -> {
            addRealmList(realmListComboBox.getSelectionModel().getSelectedItem());
            reloadComboBox();
            statusLabel.setText("RealmList added");
        });

        removeButton.setOnAction(event -> {
            removeRealmList(realmListComboBox.getSelectionModel().getSelectedItem());
            reloadComboBox();
            statusLabel.setText("RealmList removed");
        });

        applyButton.setOnAction(event -> {
            if (!WoW.getWowDirectory().equals("")) {
                WoW.exportRealmList(realmListComboBox.getSelectionModel().getSelectedItem());
                setSelectionItem(realmListComboBox.getSelectionModel().getSelectedIndex());
                statusLabel.setText("Done");
            }
        });

        applyButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    WoW.exportRealmList(realmListComboBox.getSelectionModel().getSelectedItem());
                    statusLabel.setText("Done");
                }
            }
        });

        realmListComboBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    WoW.exportRealmList(realmListComboBox.getSelectionModel().getSelectedItem());
                    statusLabel.setText("Done");
                }
            }
        });

    }

    @FXML
    void getDirectoryChooser() {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        if (!WoW.getWowDirectory().equals("")) {
            directoryChooser.setInitialDirectory(new File(WoW.getWowDirectory()));
        }
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            WoW.setWowDirectory(file.getAbsolutePath());
        }

    }

    void reloadComboBox() {
        realmListComboBox.setItems(FXCollections.observableArrayList(getRealmLists()));
    }

    public static int getSelectionItem() {
        return selectionItem;
    }

    public static void setSelectionItem(int selectionItem) {
        selectionItem = selectionItem;
    }


}



