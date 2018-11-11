package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class Controller extends DB {

    @FXML
    private ResourceBundle resources;

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

        realmListComboBox.setItems(FXCollections.observableArrayList(getRealmLists()));
        if (getRealmLists().size() == 0) {
            statusLabel.setText("RealmList not found");
        }

        saveButton.setOnAction(event -> {
            addRealmList(realmListComboBox.getSelectionModel().getSelectedItem());
            realmListComboBox.setItems(FXCollections.observableArrayList(getRealmLists()));
            statusLabel.setText("RealmList added");
        });

        removeButton.setOnAction(event -> {
            removeRealmList(realmListComboBox.getSelectionModel().getSelectedItem());
            realmListComboBox.setItems(FXCollections.observableArrayList(getRealmLists()));
            statusLabel.setText("RealmList removed");
        });
    }
}



