package ui.controller.manageBalance;

import core.facade.BankAccountFacade;
import core.models.BankAccount;
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
import java.util.Collection;
import java.util.Iterator;

/**
 * Controller of the chooseBankAccountView called when the current user has to choose
 * a BankAccount to recover his balance or a part of it.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2020-12-28
 */
public class ChooseBankAccountController {

    /**
     * Contains all the current user's BankAccount.
     */
    @FXML
    private ListView listView;

    /**
     * This method transfers the selected BankAccount in the ChooseRecoverAmountController and load the chooseRecoverAmountView.
     * It is called by a button.
     *
     * @throws IOException
     */
    public void goToChooseRecoverAmountView() throws IOException {
        if (listView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(UserNavigationPath.chooseRecoverAmountView));
            Parent root = loader.load();
            ChooseRecoverAmountController chooseRecoverAmountController = loader.getController();
            chooseRecoverAmountController.setBankAccount((BankAccount) listView.getSelectionModel().getSelectedItem());
            SplitPay.window.setScene(new Scene(root));
        }
    }

    /**
     * This method fill the listView with all the current user's BankAccount.
     * It is called at the view's load.
     */
    @FXML
    private void initialize() {
        Collection<BankAccount> bankAccounts = BankAccountFacade.getInstance().getBankAccounts();

        ObservableList<BankAccount> items = FXCollections.observableArrayList();

        Iterator<BankAccount> iterator = bankAccounts.iterator();
        while (iterator.hasNext()) {
            items.add(iterator.next());
        }
        listView.setItems(items);
    }
}
