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
import ui.path.AuthPath;
import ui.path.UserNavigationPath;
import java.io.IOException;
public class MenuController {

    @FXML
    private Button myTransactionsButton;
    @FXML
    private Button mySplitsButton;
    @FXML
    private Button notificationsButton;
    @FXML
    private Button friendsButton;
    @FXML
    private Button groupsButton;
    @FXML
    private Button personalInformationButton;
    @FXML
    private Button creditCardsButton;
    @FXML
    private Button bankAccountButton;

    @FXML
    private Label general;
    @FXML
    private Label socialLabel;
    @FXML
    private Label myInformationLabel;


    @FXML
    private void initialize() {
        if(UserFacade.getUserFacade().isStoreOwner()){
            groupsButton.setManaged(false);
            friendsButton.setManaged(false);
            creditCardsButton.setManaged(false);
            socialLabel.setManaged(false);
            socialLabel.setManaged(false);
        }
    }

    /**
     * This method redirects to the myTransactionsView
     * @throws IOException
     */
    public void goToMyTransactionsView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.myTransactionsView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the mySplitsView
     * @throws IOException
     */
    public void goToMySplitsView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.mySplitsView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the CreditCardView
     * @throws IOException
     */
    public void goToCreditCardView() throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(NormalUserNavigationPath.creditCardView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the BankAccountView
     * @throws IOException
     */
    public void goToBankAccountView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.bankAccountView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void goToGroupView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(NormalUserNavigationPath.groupView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the selectMethodView
     * @throws IOException
     */
    public void goToManageAccountView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.manageAccountView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the friendView
     * @throws IOException
     */
    public void goToFriendView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.friendView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the notificationView
     * @throws IOException
     */
    public void goToNotificationView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.notificationView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the myTransactionsView
     * @throws IOException
     */
    public void goToHomeView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.homeView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method logs the current user out and redirects him to the login page
     * @throws IOException
     */
    public void logout() throws IOException {
        UserFacade.getUserFacade().logout();
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.logInView));
        SplitPay.window.setScene(new Scene(root));
    }
}
