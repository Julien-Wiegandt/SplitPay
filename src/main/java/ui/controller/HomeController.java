package ui.controller;

import core.facade.UserFacade;
import core.models.NormalUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.SplitPay;
import ui.path.AuthPath;
import ui.path.NormalUserNavigationPath;
import ui.path.UserNavigationPath;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class HomeController {

    @FXML
    private Label balance;

    @FXML
    private VBox joinSplitSection;

    @FXML
    private Button sendMoneyToFriendButton;


    @FXML
    private void initialize() {
        balance.setText(UserFacade.getUserFacade().getLoggedUser().getBalance().toString());
        if(UserFacade.getUserFacade().isStoreOwner()){
            joinSplitSection.setVisible(false);
            joinSplitSection.setManaged(false);
            sendMoneyToFriendButton.setVisible(false);
            sendMoneyToFriendButton.setManaged(false);
        }
    }

    public void goToChooseFriendView(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(NormalUserNavigationPath.chooseFriendView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void goToManageBalanceView(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(UserNavigationPath.manageBalanceView));
        SplitPay.window.setScene(new Scene(root));
    }
}
