package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import ui.main.Paths;
import ui.main.SplitPay;

import java.io.IOException;

public class ForgottenPasswordController {

    @FXML
    private TextField credential;

    public void goToLogInView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Paths.logInView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

    public void resetPassword(ActionEvent actionEvent) {
    }
}
