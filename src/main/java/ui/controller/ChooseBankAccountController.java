package ui.controller;

import core.facade.TransactionFacade;
import core.facade.UserFacade;
import core.models.BankAccount;
import core.models.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import main.SplitPay;
import ui.path.UserNavigationPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class ChooseBankAccountController {

    @FXML
    private ListView listView;

    public void goToChooseRecoverAmountView(MouseEvent mouseEvent) throws IOException {
        if(listView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(UserNavigationPath.chooseRecoverAmountView));
            Parent root = loader.load();
            ChooseRecoverAmountController chooseRecoverAmountController = loader.getController();
            chooseRecoverAmountController.setBankAccount((BankAccount) listView.getSelectionModel().getSelectedItem());
            SplitPay.window.setScene(new Scene(root));
        }
    }

    @FXML
    private void initialize() {
        Collection<BankAccount> bankAccounts = UserFacade.getUserFacade().getBankAccounts();

        ObservableList<BankAccount> items = FXCollections.observableArrayList ();

        Iterator<BankAccount> iterator = bankAccounts.iterator();
        while (iterator.hasNext()) {
            items.add(iterator.next());
        }
        listView.setItems(items);
    }
}
