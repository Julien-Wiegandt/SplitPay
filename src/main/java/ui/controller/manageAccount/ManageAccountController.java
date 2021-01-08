package ui.controller.manageAccount;

import core.facade.UserFacade;
import core.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import main.SplitPay;
import ui.controller.manageAccount.DeleteAccountController;
import ui.path.AuthPath;

import java.io.IOException;


public class ManageAccountController {

    @FXML
    private Button changePhone;

    @FXML
    private Button changePassword;

    @FXML
    private Button changeEmail;

    @FXML
    private Button confirmEmailPhone;

    @FXML
    private Button deleteAccount;



    @FXML
    private void initialize() {
        User user = UserFacade.getUserFacade().getLoggedUser();
        if ( user.getPhone() != null && user.getEmail() != null) {
            confirmEmailPhone.setManaged(false);
        }

    }

    /**
     * This method redirects to the selectMethodView
     * @throws IOException
     */

    public void goToSelectMethodView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.selectMethodView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the changePhoneView
     * @throws IOException
     */

    public void goToChangePhoneView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.changePhoneView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the changeEmailView
     * @throws IOException
     */

    public void goToChangeEmailView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.changeEmailView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the changePasswordView
     * @throws IOException
     */

    public void goToChangePasswordView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.changePasswordView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the deleteAccountView
     * @throws IOException
     */

    public void goToDeleteAccountView() throws IOException {
        User tempUser = UserFacade.getUserFacade().getLoggedNormalUser();

        DeleteAccountController.setTempUser(tempUser);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.deleteAccountView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the confirmCredentialsView
     * @throws IOException
     */

    public void goToConfirmCredentialsView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.confirmCredentialsView));
        SplitPay.window.setScene(new Scene(root));
    }


}
