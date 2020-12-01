package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import ui.main.Paths;
import ui.main.SplitPay;

import java.io.IOException;

public class NormalUserSignUpController {

    @FXML
    private TextField email, phone, firstName, lastName, nickname;
    @FXML
    private PasswordField password1, password2;
    @FXML
    private CheckBox isCompany;
    @FXML
    private RadioButton isPhoneChecked, isEmailChecked;

    //public void normalUserSignUp(ActionEvent actionEvent) {
    //    SplitPay.user.signUp(email.getText(), phone.getText(), firstName.getText(), lastName.getText(), nickname.getText(), password1.getText());
    //}

    public void changeSignUpView(ActionEvent actionEvent) {
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
