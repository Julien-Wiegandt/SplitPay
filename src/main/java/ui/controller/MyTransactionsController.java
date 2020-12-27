package ui.controller;

import core.facade.TransactionFacade;
import core.facade.UserFacade;
import core.models.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import main.SplitPay;
import persist.dao.TransactionDAO;
import ui.path.AuthPath;
import ui.path.UserNavigationPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MyTransactionsController {
    @FXML
    private ListView listView;

    @FXML public void goToTransactionView(MouseEvent arg0) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(UserNavigationPath.transactionView));
        Parent root = loader.load();
        TransactionController transactionController = loader.getController();
        transactionController.setTransaction((Transaction) listView.getSelectionModel().getSelectedItem());
        SplitPay.window.setScene(new Scene(root));
    }

    @FXML
    private void initialize() {
        Collection transactions = TransactionFacade.getTransactionFacade().getTransactions();
        ObservableList<Transaction> items = FXCollections.observableArrayList ();

        Iterator<Transaction> iterator = transactions.iterator();
        while (iterator.hasNext()) {
            items.add(iterator.next());
        }
        listView.setItems(items);
    }

}