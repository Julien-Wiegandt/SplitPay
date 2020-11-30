package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import ui.main.SplitPay;
import ui.main.Paths;

import java.io.IOException;

public class LogInController {

    @FXML
    private TextArea credential;

    @FXML
    private PasswordField password;

    public void logIn(ActionEvent actionEvent) {
    }

    public void goToNormalUserSignInView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Paths.normalUserSignInView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

    public void goToResetPasswordView(ActionEvent actionEvent) {
    }
}
