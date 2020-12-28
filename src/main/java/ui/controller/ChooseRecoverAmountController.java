package ui.controller;

import core.facade.TransactionFacade;
import core.facade.UserFacade;
import core.models.BankAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import main.SplitPay;
import ui.path.UserNavigationPath;
import util.RegexPattern;

import java.awt.*;
import java.io.IOException;
import java.util.Date;

public class ChooseRecoverAmountController {

    private BankAccount bankAccount;

    @FXML
    private TextField amountInput;

    public void sendMoneyToBankAccount(ActionEvent actionEvent) throws IOException {
        this.allStyleSetDefault();
        if(RegexPattern.decimalPattern.matcher(amountInput.getText()).find() && UserFacade.getUserFacade().isEnoughtMoneyInBalance(Float.valueOf(amountInput.getText()))){
            UserFacade.getUserFacade().updateUserBalanceById(Integer.valueOf(UserFacade.getUserFacade().getUser().getId()), Float.valueOf(amountInput.getText())*(-1));
            TransactionFacade.getTransactionFacade().createUserToBankAccount(Float.valueOf(amountInput.getText()), new Date(), Integer.valueOf(UserFacade.getUserFacade().getUser().getId()), Integer.valueOf(bankAccount.getId()));
            Parent root = FXMLLoader.load(getClass().getResource(UserNavigationPath.homeView));
            SplitPay.window.setScene(new Scene(root));
        }else{
            amountInput.setStyle("-fx-text-box-border: red");
        }
    }

    public void setBankAccount(BankAccount selectedItem) {
        this.bankAccount = selectedItem;
    }

    private void allStyleSetDefault(){
        amountInput.setStyle("-fx-text-box-border: black");
    }
}
