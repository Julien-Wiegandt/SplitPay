package ui.controller.manageBalance;

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

import java.io.IOException;
import java.util.Date;

/**
 * Controller of the chooseRecoverAmountView called when the current user has to choose
 * a amount of money to recover on his BankAccount.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2020-12-28
 */
public class ChooseRecoverAmountController {

    /**
     * The BankAccount selected at the chooseBankAccountView, who gonna receive the money.
     */
    private BankAccount bankAccount;

    /**
     * The amount of money that will be sent by the current user to the BankAccount.
     */
    @FXML
    private TextField amountInput;

    /**
     * This method is used to send the input amount of money to the bankAccount and load the homeView.
     * It is called by a button.
     * If the amount don't respect the decimalPattern regex or
     * if there is not enough money in the current user's balance,
     * then the amountInput TextField border is highlighted in red.
     * @throws IOException
     */
    public void sendMoneyToBankAccount() throws IOException {
        this.allStyleSetDefault();
        if(RegexPattern.decimalPattern.matcher(amountInput.getText()).find() && UserFacade.getUserFacade().isEnoughtMoneyInBalance(Float.valueOf(amountInput.getText()))){
            UserFacade.getUserFacade().updateUserBalanceById(Integer.valueOf(UserFacade.getUserFacade().getUser().getId()), Float.valueOf(amountInput.getText())*(-1));
            if(UserFacade.getUserFacade().isNormalUser()){
                TransactionFacade.getTransactionFacade().createUserToBankAccount(Float.valueOf(amountInput.getText()), new Date(), Integer.valueOf(UserFacade.getUserFacade().getUser().getId()), Integer.valueOf(bankAccount.getId()));
            }else{
                TransactionFacade.getTransactionFacade().createStoreOwnerToBankAccount(Float.valueOf(amountInput.getText()), new Date(), Integer.valueOf(UserFacade.getUserFacade().getUser().getId()), Integer.valueOf(bankAccount.getId()));
            }
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.homeView));
            SplitPay.window.setScene(new Scene(root));
        }else{
            amountInput.setStyle("-fx-text-box-border: red");
        }
    }

    /**
     * This method is used to set the selected BankAccount in the chooseBankAccountView, called by the ChooseBankAccountController
     * when there is a selection.
     * @param selectedItem The selected BankAccount.
     */
    public void setBankAccount(BankAccount selectedItem) {
        this.bankAccount = selectedItem;
    }

    /**
     * This method is used to set all user's input error feedback styles to default.
     */
    private void allStyleSetDefault(){
        amountInput.setStyle("-fx-text-box-border: black");
    }
}
