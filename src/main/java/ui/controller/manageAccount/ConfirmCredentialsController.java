package ui.controller.manageAccount;

import core.facade.UserFacade;
import core.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.SplitPay;
import ui.path.AuthPath;
import util.SplitUtilities;

import java.io.IOException;

public class ConfirmCredentialsController {

    @FXML
    private Button change;

    @FXML
    private TextField credentials;

    /**
     * This method redirects to the selectMethodView
     * @throws IOException
     */
    public void goToManageAccountView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.manageAccountView));
        SplitPay.window.setScene(new Scene(root));
    }

    @FXML
    void initialize() {
        if (UserFacade.getUserFacade().getLoggedUser().getEmail() == null && UserFacade.getUserFacade().getLoggedUser().getPhone() != null) {

            credentials.setText("Enter your email");
            change.setText("Verify email");
        }

        else if (UserFacade.getUserFacade().getLoggedUser().getPhone() == null &&  UserFacade.getUserFacade().getLoggedUser().getEmail() != null) {
            credentials.setText("Enter your phone number");
            change.setText("Verify phone");
        }
        else {
            credentials.setManaged(false);
        }
    }


    public void goToVerificationView() throws IOException {
        String code = SplitUtilities.generateCode();
        User tempUser = UserFacade.getUserFacade().getLoggedUser();

            /*
            try {
                Mail.sendEmail(email1.getText(),code);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            */
        if (UserFacade.getUserFacade().getLoggedUser().getPhone() == null) {
            tempUser.setPhone(credentials.getText());
        }
        if (UserFacade.getUserFacade().getLoggedUser().getEmail() == null) {
            tempUser.setEmail(credentials.getText());
        }
        VerificationController.setTempUser(tempUser);
        System.out.println(code);
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.verificationView));
        SplitPay.window.setScene(new Scene(root));
    }
}
