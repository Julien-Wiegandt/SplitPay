package ui.controller;

import core.facade.UserFacade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.path.AuthPath;
import util.RegexPattern;
import main.SplitPay;
import java.io.IOException;

/**
 * Controller of the storeOwnerSignUpView called when a user want to sign up as a StoreOwner (not NormalUser).
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2020-12-29
 */
public class StoreOwnerSignUpController {

    /**
     * The information entered by the user.
     */
    @FXML
    private TextField credential, nickname, companyName, siret;

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
     * Create a StoreOwner with the entered information.
     * If a entered information don't match the regex pattern,
     * then the TextFields border is highlighted in red.
     */
    public void signUp() {
        allStyleSetDefault();
        if(RegexPattern.emailPattern.matcher(credential.getText()).find()
                && RegexPattern.textPattern.matcher(companyName.getText()).find()
                && RegexPattern.siretPattern.matcher(siret.getText()).find()
                && RegexPattern.nicknamePattern.matcher(nickname.getText()).find()
                && RegexPattern.passwordPattern.matcher(password1.getText()).find()
                && (password1.getText().equals(password2.getText()))){
            UserFacade.getUserFacade().storeOwnerEmailSignUp(credential.getText(), companyName.getText(), nickname.getText(), siret.getText(), password1.getText());
        }else if(RegexPattern.phonePattern.matcher(credential.getText()).find()
                && RegexPattern.textPattern.matcher(companyName.getText()).find()
                && RegexPattern.siretPattern.matcher(siret.getText()).find()
                && RegexPattern.nicknamePattern.matcher(nickname.getText()).find()
                && RegexPattern.passwordPattern.matcher(password1.getText()).find()
                && (password1.getText().equals(password2.getText()))) {
            UserFacade.getUserFacade().storeOwnerPhoneSignUp(credential.getText(), companyName.getText(), nickname.getText(), siret.getText(), password1.getText());
        }else {
            if (!RegexPattern.emailPattern.matcher(credential.getText()).find() && !RegexPattern.phonePattern.matcher(credential.getText()).find()) {
                credential.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.textPattern.matcher(companyName.getText()).find()) {
                companyName.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.nicknamePattern.matcher(nickname.getText()).find()) {
                nickname.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.siretPattern.matcher(siret.getText()).find()) {
                siret.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.passwordPattern.matcher(password1.getText()).find()
                    || !RegexPattern.passwordPattern.matcher(password2.getText()).find()
                    || !(password1.getText().equals(password2.getText()))) {
                password1.setStyle("-fx-text-box-border: red");
                password2.setStyle("-fx-text-box-border: red");
            }
        }
    }

    /**
     * This method load the normalUserSignUpView.
     * It is called by a button.
     * @throws IOException
     * @todo Handle the possible exceptions.
     */
    public void goToNormalUserSignUpView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.normalUserSignUpView));
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
        companyName.setStyle("-fx-text-box-border: black");
        siret.setStyle("-fx-text-box-border: black");
        nickname.setStyle("-fx-text-box-border: black");
        password1.setStyle("-fx-text-box-border: black");
        password2.setStyle("-fx-text-box-border: black");
    }

}
