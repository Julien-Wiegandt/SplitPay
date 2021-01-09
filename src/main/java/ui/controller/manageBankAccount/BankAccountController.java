package ui.controller.manageBankAccount;

import core.facade.BankAccountFacade;
import core.models.BankAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import main.SplitPay;
import ui.path.UserNavigationPath;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class BankAccountController {
    @FXML
    private Button addBankAccount;

    @FXML
    private ListView listView;

    @FXML
    private Button retour;


    @FXML
    private void initialize() {
        Collection transactions = BankAccountFacade.getInstance().getBankAccounts();
        ObservableList<BankAccount> items = FXCollections.observableArrayList();

        Iterator<BankAccount> iterator = transactions.iterator();
        while (iterator.hasNext()) {
            items.add(iterator.next());
        }
        listView.setItems(items);
    }

    public void goToBankAccountView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.bankAccountView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void goToAddBankAccountView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.addBankAccountView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void goToHomeView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.homeView));
        SplitPay.window.setScene(new Scene(root));
    }


    /**
     * @throws IOException
     * @todo Handle the possible exceptions. (if the user click on empty field)
     */

    public void goToEditBankAccountView() throws IOException {
        if (listView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(UserNavigationPath.editBankAccountView));
            Parent root = loader.load();
            EditBankAccountController editBankAccountController = loader.getController();
            editBankAccountController.setBankAccount((BankAccount) listView.getSelectionModel().getSelectedItem());
            SplitPay.window.setScene(new Scene(root));
        }
    }


}

