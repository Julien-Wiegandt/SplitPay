package ui.controller.manageAccount;

import core.facade.UserFacade;
import core.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import main.SplitPay;
import ui.path.AuthPath;
import util.RegexPattern;
import util.SplitUtilities;

import java.io.IOException;

public class ChangePasswordController {

    @FXML
    private PasswordField password1;

    @FXML
    private PasswordField password2;

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
     * This method change the password
     *
     * @throws IOException
     */
    public void changePassword() throws IOException {
        allStyleSetDefault();
        if (RegexPattern.passwordPattern.matcher(password1.getText()).find() && RegexPattern.passwordPattern.matcher(password2.getText()).find() && password1.getText().equals(password2.getText())) {
            String code = SplitUtilities.generateCode();
            User tempUser = UserFacade.getUserFacade().getLoggedUser();
            tempUser.setPassword(password1.getText());
            VerificationController.setTempUser(tempUser);
            System.out.println(code);

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.selectMethodView));
            SplitPay.window.setScene(new Scene(root));
        } else {
            if (!RegexPattern.passwordPattern.matcher(password1.getText()).find()) {
                password1.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.passwordPattern.matcher(password2.getText()).find()) {
                password2.setStyle("-fx-text-box-border: red");
            }
            if (!password1.getText().equals(password2.getText())) {
                password2.setStyle("-fx-text-box-border: red");
            }

        }
    }

    /**
     * This method is used to set all user's input error feedback styles to default.
     */
    private void allStyleSetDefault() {
        password1.setStyle("-fx-text-box-border: black");
        password2.setStyle("-fx-text-box-border: black");
    }
}
