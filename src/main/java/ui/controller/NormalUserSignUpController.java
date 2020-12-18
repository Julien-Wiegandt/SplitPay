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

public class NormalUserSignUpController {

    @FXML
    private TextField credential, firstName, lastName, nickname;
    @FXML
    private PasswordField password1, password2;
    @FXML
    private CheckBox isCompany;

    public void signUp(ActionEvent actionEvent) {
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

    public void goToStoreOwnerSignUpView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.storeOwnerSignUpView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

    public void goToLogInView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.logInView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

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
