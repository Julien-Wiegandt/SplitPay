package ui.controller;

import core.facade.UserFacade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import main.SplitPay;
import ui.path.UserNavigationPath;

import java.io.IOException;

/**
 * Controller of the manageBalanceView called when the current user want to manage his balance.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2020-12-29
 */
public class ManageBalanceController {

    /**
     * The current user's balance amount.
     */
    @FXML
    private Label balanceAmount;

    /**
     * This method load the chooseCreditCardView.
     * It is called by a button.
     * @throws IOException
     * @todo Handle the possible exceptions.
     */
    public void goToChooseCreditCardView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(UserNavigationPath.chooseCreditCardView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method load the chooseBankAccountView.
     * It is called by a button.
     * @throws IOException
     * @todo Handle the possible exceptions.
     */
    public void goToChooseBankAccountView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(UserNavigationPath.chooseBankAccountView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method set the current user's balance amount.
     * It is called at the view's load.
     */
    @FXML
    private void initialize() {
        balanceAmount.setText(UserFacade.getUserFacade().getLoggedUser().getBalance().toString());
    }
}
