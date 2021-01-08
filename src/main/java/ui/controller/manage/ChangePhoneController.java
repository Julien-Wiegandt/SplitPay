package ui.controller.manage;

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
import util.SplitUtilities;

import java.io.IOException;

public class ChangePhoneController {



    @FXML
    private Label phoneLabel;


    @FXML
    private TextField phone;



    @FXML
    private Button cancel;

    @FXML
    private Button confirm;


    @FXML
    void initialize() {


    }

    /**
     * This method creates a tempUser with the new phone number and go to verificationView to validate.
     * @param actionEvent
     * @throws IOException
     */
    public void changePhone(ActionEvent actionEvent) throws IOException {
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
