package ui.controller;

import core.facade.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.SplitPay;
import ui.path.NormalUserNavigationPath;
import ui.path.StoreOwnerNavigationPath;
import ui.path.UserNavigationPath;

import java.io.IOException;
import java.sql.SQLException;

public class HomeController {

    @FXML
    private Label balance;

    @FXML
    private Button sendMoneyToFriendButton;

    @FXML
    private Button generateSplitButton;


    @FXML
    private void initialize() {
        if (UserFacade.getUserFacade().isNormalUser()) {
            try {
                UserFacade.getUserFacade().setLoggedUser(UserFacade.getUserFacade().findNormalUserById(Integer.valueOf(UserFacade.getUserFacade().getUser().getId())));
            } catch (SQLException throwables) {
                System.out.println("Can't find the logged NormalUser in the db.");
            }
            generateSplitButton.setVisible(false);
        } else {
            try {
                UserFacade.getUserFacade().setLoggedUser(UserFacade.getUserFacade().findStoreOwnerById(Integer.valueOf(UserFacade.getUserFacade().getUser().getId())));
            } catch (SQLException throwables) {
                System.out.println("Can't find the logged NormalUser in the db.");
            }
            sendMoneyToFriendButton.setVisible(false);
        }
        balance.setText(UserFacade.getUserFacade().getLoggedUser().getBalance().toString() + "€");
    }

    public void goToChooseFriendView(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(NormalUserNavigationPath.chooseFriendView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void goToManageBalanceView(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.manageBalanceView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void goToGenerateSplitView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(StoreOwnerNavigationPath.generateSplitView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the myTransactionsView
     *
     * @throws IOException
     */
    public void goToHomeView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(UserNavigationPath.homeView));
        SplitPay.window.setScene(new Scene(root));
    }
}
