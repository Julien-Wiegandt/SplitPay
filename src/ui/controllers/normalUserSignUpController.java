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
import ui.main.RegexPattern;
import ui.main.SplitPay;

import java.io.IOException;

public class normalUserSignUpController {

    @FXML
    private TextField email, phone, firstName, lastName, nickname;
    @FXML
    private PasswordField password1, password2;
    @FXML
    private CheckBox isCompany;
    @FXML
    private RadioButton phoneValidation, emailValidation;

    public void signUp(ActionEvent actionEvent) {
        allStyleSetDefault();
        if(RegexPattern.emailPattern.matcher(email.getText()).find()
            && RegexPattern.phonePattern.matcher(phone.getText()).find()
            && RegexPattern.textPattern.matcher(firstName.getText()).find()
            && RegexPattern.textPattern.matcher(lastName.getText()).find()
            && RegexPattern.nicknamePattern.matcher(nickname.getText()).find()
            && RegexPattern.passwordPattern.matcher(password1.getText()).find()
            && (password1.getText().equals(password2.getText()))
            && (phoneValidation.isSelected() || emailValidation.isSelected())
            && !(phoneValidation.isSelected() && emailValidation.isSelected())){
            //SplitPay.user.normalUserSignUp(email.getText(), phone.getText(), firstName.getText(), lastName.getText(), nickname.getText(), password1.getText());
        }else {
            if (!RegexPattern.emailPattern.matcher(email.getText()).find()) {
                email.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.phonePattern.matcher(phone.getText()).find()) {
                phone.setStyle("-fx-text-box-border: red");
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
            if((phoneValidation.isSelected() && emailValidation.isSelected())
                || (!phoneValidation.isSelected() && !emailValidation.isSelected())) {
                phoneValidation.setStyle("-fx-text-fill: red");
                emailValidation.setStyle("-fx-text-fill: red");
            }
        }
    }

    public void goToStoreOwnerSignUpView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Paths.storeOwnerSignUpView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

    public void verificationByPhoneChecked(ActionEvent actionEvent) {
        if(emailValidation.isSelected()){
            emailValidation.setSelected(false);
        }
    }

    public void verificationByEmailChecked(ActionEvent actionEvent) {
        if(phoneValidation.isSelected()){
            phoneValidation.setSelected(false);
        }
    }

    public void goToLogInView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Paths.logInView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

    private void allStyleSetDefault(){
        email.setStyle("-fx-text-box-border: black");
        phone.setStyle("-fx-text-box-border: black");
        firstName.setStyle("-fx-text-box-border: black");
        lastName.setStyle("-fx-text-box-border: black");
        nickname.setStyle("-fx-text-box-border: black");
        password1.setStyle("-fx-text-box-border: black");
        password2.setStyle("-fx-text-box-border: black");
        phoneValidation.setStyle("-fx-text-fill: black");
        emailValidation.setStyle("-fx-text-fill: black");
    }
}
