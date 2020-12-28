package ui.controller;

import core.facade.TransactionFacade;
import core.facade.UserFacade;
import core.models.CreditCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import main.SplitPay;
import ui.path.UserNavigationPath;
import util.RegexPattern;

import java.io.IOException;
import java.util.Date;

public class ChooseRefillAmountController {

    @FXML
    private TextField amountInput;

    private CreditCard creditCard;

    public void setCreditCard(CreditCard selectedItem) {
        this.creditCard = selectedItem;
    }

    public void sendMoneyToBalance(ActionEvent actionEvent) throws IOException {
        this.allStyleSetDefault();
        if(RegexPattern.decimalPattern.matcher(amountInput.getText()).find()){
            UserFacade.getUserFacade().updateUserBalanceById(Integer.valueOf(UserFacade.getUserFacade().getUser().getId()), Float.valueOf(amountInput.getText()));
            TransactionFacade.getTransactionFacade().createCreditCardToUserTransaction(Float.valueOf(amountInput.getText()), new Date(), Integer.valueOf(UserFacade.getUserFacade().getUser().getId()), Integer.valueOf(creditCard.getDbId()));
            Parent root = FXMLLoader.load(getClass().getResource(UserNavigationPath.homeView));
            SplitPay.window.setScene(new Scene(root));
        }else{
            amountInput.setStyle("-fx-text-box-border: red");
        }
    }

    private void allStyleSetDefault(){
        amountInput.setStyle("-fx-text-box-border: black");
    }

}
