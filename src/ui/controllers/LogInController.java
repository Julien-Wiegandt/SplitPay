package ui.controllers;

import java.util.regex.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.main.RegexPattern;
import ui.main.SplitPay;
import ui.main.Paths;

import java.io.IOException;

public class LogInController {

    @FXML
    private TextField credential;
    @FXML
    private PasswordField password;
    @FXML
    private Label credentialError, passwordError;

    /**
     * This method log in the user in the SplitPay application if the regex patterns are
     * respected, else the wrong fields will be highlighted in red.
     * @param actionEvent
     */
    public void logIn(ActionEvent actionEvent) {
        //If the email and password match with their regex -> login with email
        if(RegexPattern.emailPattern.matcher(credential.getText()).find()
                && RegexPattern.passwordPattern.matcher(password.getText()).find()) {
            SplitPay.user.emailLogin(credential.getText(), password.getText());
        }//If the phone and password match with their regex -> login with phone
        else if(RegexPattern.phonePattern.matcher(credential.getText()).find()
                && RegexPattern.passwordPattern.matcher(password.getText()).find()){
            SplitPay.user.phoneLogin(credential.getText(), password.getText());
        }//Else show in red where the regex are not respected
        else{
            if(!RegexPattern.emailPattern.matcher(credential.getText()).find() || !RegexPattern.phonePattern.matcher(credential.getText()).find()){ credential.setStyle("-fx-text-box-border: red");}
            if(!RegexPattern.passwordPattern.matcher(password.getText()).find()){ password.setStyle("-fx-text-box-border: red");}
        }
    }

    /**
     * This method redirects to the normalUserSignUpView
     * @param actionEvent
     * @throws IOException
     */
    public void goToNormalUserSignUpView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Paths.normalUserSignUpView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

    /**
     * This method redirects to the forgottenPasswordView
     * @param actionEvent
     * @throws IOException
     */
    public void goToForgottenPasswordView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Paths.forgottenPasswordView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

}
