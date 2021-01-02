package ui.controller;

import core.auth.Session;
import core.facade.FriendFacade;
import core.facade.UserFacade;
import core.models.NormalUser;
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
import utilities.Mail;
import utilities.RegexPattern;
import utilities.SplitUtilities;

import java.io.IOException;
import java.sql.SQLException;

public class ChangePhoneController {

    @FXML
    private Label verificationLabel;

    @FXML
    private Label phoneLabel;


    @FXML
    private TextField phone;

    @FXML
    private TextField verification;

    @FXML
    private Button cancel;

    @FXML
    private Button confirm;

    @FXML
    private Button validate;

    private String code;

    @FXML
    void initialize() {

        verification.setManaged(false);
        verificationLabel.setManaged(false);
        validate.setManaged(false);

    }

    /**
     * This method creates a tempUser with the new phone number and go to verificationView to validate.
     * @param actionEvent
     * @throws IOException
     */
    public void changePhone(ActionEvent actionEvent) throws IOException {
        if (RegexPattern.phonePattern.matcher(phone.getText()).find()) {
            code = SplitUtilities.generateCode();
            System.out.println(code);
            User tempUser = UserFacade.getUserFacade().getLoggedNormalUser();
            tempUser.setValidationCode(code);
            tempUser.setPhone(phone.getText());
            VerificationController.setTempUser(tempUser);
            Parent root = FXMLLoader.load(getClass().getResource(AuthPath.verificationView));
            SplitPay.window.setScene(new Scene(root));

        }
        else {
            phone.setStyle("-fx-text-box-border: red");
        }


    }

    /**
     * This method check if the validation code is correct and set the new user phone number
     * @param actionEvent
     * @throws IOException
     */
    public void validate(ActionEvent actionEvent) throws IOException {
        if (code.equals(verification.getText())){
            NormalUser user = UserFacade.getUserFacade().getLoggedNormalUser();
            user.setPhone(phone.getText());
            UserFacade.getUserFacade().updateUser(user);
            System.out.println(user);
        }


    }
}
