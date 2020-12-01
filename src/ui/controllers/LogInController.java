package ui.controllers;

import core.UserFaçade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.main.SplitPay;
import ui.main.Paths;

import java.io.IOException;

public class LogInController {

    @FXML
    private TextField credential;
    @FXML
    private PasswordField password;

    public void logIn(ActionEvent actionEvent) {
        SplitPay.user.login(credential.getText(), password.getText());
    }

    public void goToNormalUserSignUpView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Paths.normalUserSignInView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

    public void goToForgottenPasswordView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Paths.forgottenPasswordView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }
}
