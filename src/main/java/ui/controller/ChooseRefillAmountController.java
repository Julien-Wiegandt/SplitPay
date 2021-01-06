package ui.controller;

import core.facade.TransactionFacade;
import core.facade.UserFacade;
import core.models.CreditCard;
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

/**
 * Controller of the chooseRefillAmountView called when the current user has to choose
 * a amount of money to refill his balance with the selected CreditCard.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2020-12-28
 */
public class ChooseRefillAmountController {

    /**
     * The CreditCard selected at the chooseCreditCardView, who's going to send the money.
     */
    private CreditCard creditCard;

    /**
     * The amount of money that will be sent by the creditCard to the current user's balance.
     */
    @FXML
    private TextField amountInput;

    /**
     * This method is used to set the selected CreditCard in the chooseCreditCardView, called by the ChooseCreditCardtController
     * when there is a selection.
     * @param selectedItem The selected CreditCard.
     */
    public void setCreditCard(CreditCard selectedItem) {
        this.creditCard = selectedItem;
    }

    /**
     * This method is used to send the input amount of money from the CreditCard to the current user's balance and load the homeView.
     * It is called by a button.
     * If the amount don't respect the decimalPattern regex or
     * if there is not enough money in the current user's CreditCard,
     * then the amountInput TextField border is highlighted in red.
     * @throws IOException
     * @todo Handle the possible exceptions.
     */
    public void sendMoneyToBalance() throws IOException {
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

    /**
     * This method is used to set all user's input error feedback styles to default.
     */
    private void allStyleSetDefault(){
        amountInput.setStyle("-fx-text-box-border: black");
    }

}
