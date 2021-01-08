package ui.controller;

import core.facade.UserFacade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.path.AuthPath;
import ui.path.UserNavigationPath;
import util.RegexPattern;
import main.SplitPay;

import java.io.IOException;

/**
 * Controller of the logInView called when a user want to log In.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2020-12-28
 */
public class LogInController {

    /**
     * The credential entered by the user to log In(expected: e-mail adress or phone).
     */
    @FXML
    private TextField credential;

    /**
     * The password entered by the user to log In.
     */
    @FXML
    private PasswordField password;

    /**
     * This method log in the user in the SplitPay.SplitPay application if the regex patterns are
     * respected, else the wrong fields will be highlighted in red.
     */
    public void logIn() {
        this.allStyleSetDefault();
        //If the email and password match with their regex -> login with email
        if(RegexPattern.emailPattern.matcher(credential.getText()).find()
                && RegexPattern.passwordPattern.matcher(password.getText()).find()) {
            try {
                UserFacade.getUserFacade().emailLogIn(credential.getText(), password.getText());
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.homeView));
                SplitPay.window.setScene(new Scene(root));
            } catch (Exception e) {
                password.setText("");
                password.setStyle("-fx-text-box-border: red");
            }
        }//If the phone and password match with their regex -> login with phone
        else if(RegexPattern.phonePattern.matcher(credential.getText()).find()
                && RegexPattern.passwordPattern.matcher(password.getText()).find()){
            try {
                UserFacade.getUserFacade().phoneLogIn(credential.getText(), password.getText());
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.homeView));
                SplitPay.window.setScene(new Scene(root));
            } catch (Exception e) {
                password.setText("");
                password.setStyle("-fx-text-box-border: red");
            }
        }//Else show in red where the regex are not respected
        else{
            if(!RegexPattern.emailPattern.matcher(credential.getText()).find() && !RegexPattern.phonePattern.matcher(credential.getText()).find()){ credential.setStyle("-fx-text-box-border: red");}
            if(!RegexPattern.passwordPattern.matcher(password.getText()).find()){ password.setStyle("-fx-text-box-border: red");}
        }
    }

    /**
     * This method redirects to the normalUserSignUpView.
     * It is called by a button.
     * @throws IOException
     * @todo Handle the possible exceptions.
     */
    public void goToNormalUserSignUpView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.normalUserSignUpView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

    /**
     * This method redirects to the forgottenPasswordView.
     * It is called by a button.
     * @throws IOException
     */
    public void goToForgottenPasswordView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.forgottenPasswordView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

    /**
     * This method is used to set all user's input error feedback styles to default.
     */
    private void allStyleSetDefault(){
        credential.setStyle("-fx-text-box-border: black");
        password.setStyle("-fx-text-box-border: black");
    }

}
