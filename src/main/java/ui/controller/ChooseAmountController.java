package ui.controller;

import core.auth.Session;
import core.facade.TransactionFacade;
import core.facade.UserFacade;
import core.models.NormalUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import main.SplitPay;
import ui.path.AuthPath;
import ui.path.UserNavigationPath;
import util.RegexPattern;

import java.io.IOException;
import java.util.Date;

public class ChooseAmountController {

    private NormalUser friend;

    @FXML
    private TextField amountInput;

    @FXML
    private void initialize() {
    }

    public void setFriend(NormalUser friend){
        this.friend = friend;
    }

    public void sendMoneyToFriend(javafx.event.ActionEvent actionEvent) throws IOException {
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

    private void allStyleSetDefault(){
        amountInput.setStyle("-fx-text-box-border: black");
    }
}