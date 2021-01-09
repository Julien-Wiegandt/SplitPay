package ui.controller.transaction;

import core.facade.TransactionFacade;
import core.models.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import main.SplitPay;
import ui.path.UserNavigationPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Controller of the myTransactionsView called when the current user want to see his transaction history.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2020-12-29
 */
public class MyTransactionsController {

    /**
     * Contains all the current user's Transactions.
     */
    @FXML
    private ListView listView;

    /**
     * This method transfers the selected Transaction in the TransactionController and load the transactionView.
     * It is called by a button.
     *
     * @throws IOException
     */
    @FXML
    public void goToTransactionView() throws IOException {
        if (listView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(UserNavigationPath.transactionView));
            Parent root = loader.load();
            TransactionController transactionController = loader.getController();
            transactionController.setTransaction((Transaction) listView.getSelectionModel().getSelectedItem());
            SplitPay.window.setScene(new Scene(root));
        }
    }

    /**
     * This method fill the listView with all the current user's Transactions (sorted in descending order of dates).
     * It is called at the view's load.
     */
    @FXML
    private void initialize() {
        Collection<Transaction> res = TransactionFacade.getTransactionFacade().getTransactions();
        ArrayList<Transaction> transactions = new ArrayList<Transaction>(res);
        Collections.sort(transactions, Collections.reverseOrder());

        ObservableList<Transaction> items = FXCollections.observableArrayList();

        Iterator<Transaction> iterator = transactions.iterator();
        while (iterator.hasNext()) {
            items.add(iterator.next());
        }
        listView.setItems(items);
    }
}