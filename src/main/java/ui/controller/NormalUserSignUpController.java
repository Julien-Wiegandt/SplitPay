package ui.controller;

import ui.path.AuthPath;
import main.SplitPay;
import core.facade.UserFacade;
import util.RegexPattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;

/**
 * Controller of the chooseBankAccountView called when a user want to sign up as a NormalUser (not StoreOwner).
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2020-12-29
 */
public class NormalUserSignUpController {

    /**
     * The information entered by the user.
     */
    @FXML
    private TextField credential, firstName, lastName, nickname;
    /**
     * The password entered by the user.
     */
    @FXML
    private PasswordField password1, password2;

    /**
     * CheckBox checked if the user want to sign up as a StoreOwner.
     */
    @FXML
    private CheckBox isCompany;

    /**
     * Create a NormalUser with the entered information.
     * If a entered information don't match the regex pattern,
     * then the TextFields border is highlighted in red.
     */
    public void signUp() {
        allStyleSetDefault();
        if(RegexPattern.emailPattern.matcher(credential.getText()).find()
                && RegexPattern.textPattern.matcher(firstName.getText()).find()
                && RegexPattern.textPattern.matcher(lastName.getText()).find()
                && RegexPattern.nicknamePattern.matcher(nickname.getText()).find()
                && RegexPattern.passwordPattern.matcher(password1.getText()).find()
                && (password1.getText().equals(password2.getText()))){
            UserFacade.getUserFacade().normalUserEmailSignUp(credential.getText(), firstName.getText(), lastName.getText(), nickname.getText(), password1.getText());
        }else if(RegexPattern.phonePattern.matcher(credential.getText()).find()
                && RegexPattern.textPattern.matcher(firstName.getText()).find()
                && RegexPattern.textPattern.matcher(lastName.getText()).find()
                && RegexPattern.nicknamePattern.matcher(nickname.getText()).find()
                && RegexPattern.passwordPattern.matcher(password1.getText()).find()
                && (password1.getText().equals(password2.getText()))) {
            UserFacade.getUserFacade().normalUserPhoneSignUp(credential.getText(), firstName.getText(), lastName.getText(), nickname.getText(), password1.getText());
        } else {
            if (!RegexPattern.emailPattern.matcher(credential.getText()).find() && !RegexPattern.phonePattern.matcher(credential.getText()).find()) {
                credential.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.textPattern.matcher(firstName.getText()).find()) {
                firstName.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.textPattern.matcher(lastName.getText()).find()) {
                lastName.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.nicknamePattern.matcher(nickname.getText()).find()) {
                nickname.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.passwordPattern.matcher(password1.getText()).find()
                || !(password1.getText().equals(password2.getText()))) {
                password1.setStyle("-fx-text-box-border: red");
                password2.setStyle("-fx-text-box-border: red");
            }
        }
    }

    /**
     * This method load the storeOwnerSignUpView.
     * It is called by a button.
     * @throws IOException
     * @todo Handle the possible exceptions.
     */
    public void goToStoreOwnerSignUpView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.storeOwnerSignUpView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

    /**
     * This method load the logInView.
     * It is called by a button.
     * @throws IOException
     * @todo Handle the possible exceptions.
     */
    public void goToLogInView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.logInView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

    /**
     * This method is used to set all user's input error feedback styles to default.
     */
    private void allStyleSetDefault(){
        credential.setStyle("-fx-text-box-border: black");
        credential.setStyle("-fx-text-box-border: black");
        firstName.setStyle("-fx-text-box-border: black");
        lastName.setStyle("-fx-text-box-border: black");
        nickname.setStyle("-fx-text-box-border: black");
        password1.setStyle("-fx-text-box-border: black");
        password2.setStyle("-fx-text-box-border: black");
    }
}
