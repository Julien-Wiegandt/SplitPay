package ui.controller;

import core.facade.BankAccountFacade;
import core.facade.CreditCardFacade;
import core.models.BankAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import main.SplitPay;
import ui.path.NormalUserNavigationPath;
import ui.path.UserNavigationPath;

import java.io.IOException;

public class EditBankAccountController {

    @FXML
    private Label label;

    @FXML
    private Label ownerFirstName;

    @FXML
    private Label ownerLastName;

    @FXML
    private Label iban;


    private String iban_bankAccount;

    public void setBankAccount(BankAccount bankAccount){

        this.iban_bankAccount = bankAccount.getIban();
        label.setText(bankAccount.getLabel());
        ownerFirstName.setText(bankAccount.getOwnerFirstName());
        ownerLastName.setText(bankAccount.getOwnerLastName());
        iban.setText(bankAccount.getIban());

    }


    public void deleteBankAccount(ActionEvent actionEvent) throws IOException {
        BankAccountFacade.getInstance().deleteBankAccount(this.iban_bankAccount);
        Parent root = FXMLLoader.load(getClass().getResource(UserNavigationPath.bankAccountView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void goToBankAccountView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(UserNavigationPath.bankAccountView));
        SplitPay.window.setScene(new Scene(root));
    }
}
