package ui.controller;

import core.facade.UserFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class TransactionController {
    @FXML
    private ListView listView;


    @FXML
    private void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList ("A", "B", "C", "D");
        listView.setItems(items);
    }

}