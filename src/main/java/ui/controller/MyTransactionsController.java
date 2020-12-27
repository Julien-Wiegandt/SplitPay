package ui.controller;

import core.facade.TransactionFacade;
import core.facade.UserFacade;
import core.models.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import persist.dao.TransactionDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class TransactionController {
    @FXML
    private ListView listView;


    @FXML
    private void initialize() {
        Collection transactions = TransactionFacade.getTransactionFacade().getTransactions();
        ObservableList<String> items = FXCollections.observableArrayList ();

        Iterator<Transaction> iterator = transactions.iterator();
        while (iterator.hasNext()) {
            items.add(iterator.next().toString());
        }
        listView.setItems(items);
    }

}