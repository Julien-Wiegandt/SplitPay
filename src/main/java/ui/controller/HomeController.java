package ui.controller;

import core.auth.Session;
import core.facade.UserFacade;
import core.models.NormalUser;
import javafx.event.ActionEvent;
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
import java.io.IOException;
import java.sql.SQLException;

public class HomeController {

    @FXML
    private Label balance;

    @FXML
    private Button sendMoneyToFriendButton;


    @FXML
    private void initialize() {
        if(UserFacade.getUserFacade().isNormalUser()){
            try {
                UserFacade.getUserFacade().setLoggedUser(UserFacade.getUserFacade().findNormalUserById(Integer.valueOf(UserFacade.getUserFacade().getUser().getId())));
            }catch (SQLException throwables) {
                System.out.println("Can't find the logged NormalUser in the db.");
            }
        }
        else{
            try {
                UserFacade.getUserFacade().setLoggedUser(UserFacade.getUserFacade().findStoreOwnerById(Integer.valueOf(UserFacade.getUserFacade().getUser().getId())));
            }catch (SQLException throwables) {
                System.out.println("Can't find the logged NormalUser in the db.");
            }
            sendMoneyToFriendButton.setVisible(false);
        }
        balance.setText(UserFacade.getUserFacade().getLoggedUser().getBalance().toString()+"€");
    }

    public void goToChooseFriendView(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(NormalUserNavigationPath.chooseFriendView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void goToManageBalanceView(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.manageBalanceView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the myTransactionsView
     * @param actionEvent
     * @throws IOException
     */
    public void goToHomeView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(UserNavigationPath.homeView));
        SplitPay.window.setScene(new Scene(root));
    }
}
