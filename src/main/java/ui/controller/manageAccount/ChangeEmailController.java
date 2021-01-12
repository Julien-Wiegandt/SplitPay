package ui.controller.manageAccount;

import core.facade.UserFacade;
import core.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import main.SplitPay;
import ui.path.AuthPath;
import util.RegexPattern;

import java.io.IOException;

public class ChangeEmailController {

    @FXML
    private TextField email1;
    @FXML
    private TextField email2;

    /**
     * This method redirects to the selectMethodView
     *
     * @throws IOException
     */
    public void goToManageAccountView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.manageAccountView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method changes the user's email
     *
     * @throws IOException
     */
    public void changeEmail() throws IOException {
        allStyleSetDefault();
        if (RegexPattern.emailPattern.matcher(email1.getText()).find() && RegexPattern.emailPattern.matcher(email2.getText()).find() && email1.getText().equals(email2.getText())) {
            User tempUser = UserFacade.getUserFacade().getLoggedUser();
            tempUser.setEmail(email1.getText());
            VerificationController.setTempUser(tempUser);

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.verificationView));
            SplitPay.window.setScene(new Scene(root));
        } else {
            if (!RegexPattern.emailPattern.matcher(email1.getText()).find()) {
                email1.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.emailPattern.matcher(email2.getText()).find()) {
                email2.setStyle("-fx-text-box-border: red");
            }
            if (!email1.getText().equals(email2.getText())) {
                email2.setStyle("-fx-text-box-border: red");
            }
        }
    }

    /**
     * This method is used to set all user's input error feedback styles to default.
     */
    private void allStyleSetDefault() {
        email1.setStyle("-fx-text-box-border: black");
        email2.setStyle("-fx-text-box-border: black");
    }

}
