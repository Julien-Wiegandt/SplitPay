package ui.controller.manageAccount;

import core.facade.UserFacade;
import core.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.SplitPay;
import ui.path.AuthPath;
import util.RegexPattern;

import java.io.IOException;

public class ChangePhoneController {

    @FXML
    private TextField phone;


    /**
     * This method redirects to the selectMethodView
     * @throws IOException
     */
    public void goToManageAccountView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.manageAccountView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method creates a tempUser with the new phone number and go to verificationView to validate.
     * @throws IOException
     */
    public void changePhone() throws IOException {
        if (RegexPattern.phonePattern.matcher(phone.getText()).find()) {
            User tempUser = UserFacade.getUserFacade().getLoggedNormalUser();
            tempUser.setPhone(phone.getText());
            VerificationController.setTempUser(tempUser);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.verificationView));
            SplitPay.window.setScene(new Scene(root));

        }
        else {
            phone.setStyle("-fx-text-box-border: red");
        }
    }

}
