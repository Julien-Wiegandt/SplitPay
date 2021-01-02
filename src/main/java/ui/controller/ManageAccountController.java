package ui.controller;

import core.facade.UserFacade;
import core.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.SplitPay;
import ui.path.AuthPath;
import util.SplitUtilities;

import java.io.IOException;

public class ManageAccountController {










    /**
     * This method redirects to the selectMethodView
     * @param actionEvent
     * @throws IOException
     */

    public void goToSelectMethodView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.selectMethodView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the changePhoneView
     * @param actionEvent
     * @throws IOException
     */

    public void goToChangePhoneView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.changePhoneView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the changeEmailView
     * @param actionEvent
     * @throws IOException
     */

    public void goToChangeEmailView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.changeEmailView));
        SplitPay.window.setScene(new Scene(root));
    }



    /**
     * This method redirects to the changePasswordView
     * @param actionEvent
     * @throws IOException
     */

    public void goToChangePasswordView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.changePasswordView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the deleteAccountView
     * @param actionEvent
     * @throws IOException
     */

    public void goToDeleteAccountView(ActionEvent actionEvent) throws IOException {
        String code  = SplitUtilities.generateCode();
        System.out.println(code);
        User tempUser = UserFacade.getUserFacade().getLoggedNormalUser();
        tempUser.setValidationCode(code);

        DeleteAccountController.setTempUser(tempUser);
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.deleteAccountView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the confirmCredentialsView
     * @param actionEvent
     * @throws IOException
     */

    public void goToConfirmCredentialsView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.confirmCredentialsView));
        SplitPay.window.setScene(new Scene(root));
    }


}
