package ui.controller;

import core.facade.TransactionFacade;
import core.facade.UserFacade;
import core.models.NormalUser;
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
 * Controller of the chooseAmountView called when the current user has to choose
 * the amount of money to send to a friend.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2020-12-28
 */
public class ChooseAmountController {

    /**
     * The friend selected at the chooseFriendView, who gonna receive the money.
     */
    private NormalUser friend;

    /**
     * The amount of money that will be sent by the current user to the friend.
     */
    @FXML
    private TextField amountInput;

    /**
     * This method is used to set the selected friend in the chooseFriendView, called by the ChooseFriendController
     * when there is a selection.
     * @param friend The selected friend
     */
    public void setFriend(NormalUser friend){
        this.friend = friend;
    }

    /**
     * This method is used to send the input amount of money to the friend and load the homeView.
     * It is called by a button.
     * If the amount don't respect the decimalPattern regex or
     * if there is not enough money in the current user's balance,
     * then the amountInput TextField border is highlighted in red.
     * @throws IOException
     * @todo Handle the possible exceptions.
     */
    public void sendMoneyToFriend() throws IOException {
        this.allStyleSetDefault();
        if(RegexPattern.decimalPattern.matcher(amountInput.getText()).find() && UserFacade.getUserFacade().isEnoughtMoneyInBalance(Float.valueOf(amountInput.getText()))){
            UserFacade.getUserFacade().updateUserBalanceById(Integer.valueOf(UserFacade.getUserFacade().getUser().getId()), Float.valueOf(amountInput.getText())*(-1));
            UserFacade.getUserFacade().updateUserBalanceById(Integer.valueOf(friend.getId()), Float.valueOf(amountInput.getText()));
            TransactionFacade.getTransactionFacade().createUserToUserTransaction(Float.valueOf(amountInput.getText()), new Date(), Integer.valueOf(UserFacade.getUserFacade().getUser().getId()), Integer.valueOf(friend.getId()));
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