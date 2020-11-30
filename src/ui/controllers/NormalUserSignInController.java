package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import ui.main.Paths;
import ui.main.SplitPay;

import java.io.IOException;

public class NormalUserSignInController {
    public void normalUserSignIn(ActionEvent actionEvent) {
    }

    public void changeSignInView(ActionEvent actionEvent) {
    }

    public void verificationByPhoneChecked(ActionEvent actionEvent) {
    }

    public void verificationByEmailChecked(ActionEvent actionEvent) {
    }

    public void goToLogInView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Paths.logInView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }
}
