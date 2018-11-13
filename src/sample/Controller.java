package sample;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
                WoW.saveToWoWRealmlist(realmListComboBox.getSelectionModel().getSelectedItem());
                setSelectionItem(realmListComboBox.getSelectionModel().getSelectedIndex());
                SaveLoadFile.save();
                statusLabel.setText("Done");
            }
        });

        applyButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    WoW.saveToWoWRealmlist(realmListComboBox.getSelectionModel().getSelectedItem());
                    statusLabel.setText("Done");
                }
            }
        });

        realmListComboBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    WoW.saveToWoWRealmlist(realmListComboBox.getSelectionModel().getSelectedItem());
                    statusLabel.setText("Done");
                }
            }
        });

        realmListComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setSelectionItem(realmListComboBox.getSelectionModel().getSelectedIndex());
            }
        });

        startingInitialisation();
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
            String importedRealmlist = WoW.loadFromWoWRealmlist();
            if (!importedRealmlist.equals("")) {
                addRealmList(importedRealmlist);
                reloadComboBox();
                realmListComboBox.getSelectionModel().select(importedRealmlist);
            }
        }

    }

    void reloadComboBox() {
        realmListComboBox.setItems(FXCollections.observableArrayList(getRealmLists()));
    }

    public static int getSelectionItem() {
        return selectionItem;
    }

    public static void setSelectionItem(int currentItem) {
        selectionItem = currentItem;
    }

    private void startingInitialisation() {
        SaveLoadFile.load();
        String importFromWoW;
        importFromWoW = WoW.loadFromWoWRealmlist();
        if (!importFromWoW.equals("")) {
            addRealmList(importFromWoW);
            statusLabel.setText("RealmList found");
        } else {
            statusLabel.setText("WoW directory not found");
        }
        reloadComboBox();
        realmListComboBox.getSelectionModel().select(selectionItem);
    }
}



