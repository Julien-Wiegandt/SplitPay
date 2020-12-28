package ui.controller;

import core.facade.UserFacade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.SplitPay;
import ui.path.NormalUserNavigationPath;
import ui.path.UserNavigationPath;

import java.io.IOException;

public class ManageBalanceController {
    @FXML
    private Label balanceAmount;


    @FXML
    private void initialize() {
        balanceAmount.setText(UserFacade.getUserFacade().getLoggedUser().getBalance().toString());
    }

    public void goToChooseCreditCardView(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(UserNavigationPath.chooseCreditCardView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void goToChooseBankAccountView(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(UserNavigationPath.chooseBankAccountView));
        SplitPay.window.setScene(new Scene(root));
    }
}
