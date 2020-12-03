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

public class StoreOwnerSignUpController {

    @FXML
    private TextField email, phone, nickname, companyName, siret;
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
                && RegexPattern.textPattern.matcher(companyName.getText()).find()
                && RegexPattern.siretPattern.matcher(siret.getText()).find()
                && RegexPattern.nicknamePattern.matcher(nickname.getText()).find()
                && RegexPattern.passwordPattern.matcher(password1.getText()).find()
                && (password1.getText().equals(password2.getText()))
                && (phoneValidation.isSelected() || emailValidation.isSelected())
                && !(phoneValidation.isSelected() && emailValidation.isSelected())){
            //SplitPay.user.storeOwnerSignUp(email.getText(), phone.getText(), companyName.getText(), nickname.getText(), siret.getText(), password1.getText());
        }else {
            if (!RegexPattern.emailPattern.matcher(email.getText()).find()) {
                email.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.phonePattern.matcher(phone.getText()).find()) {
                phone.setStyle("-fx-text-box-border: red");
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
            if((phoneValidation.isSelected() && phoneValidation.isSelected())
                    || (!phoneValidation.isSelected() && !phoneValidation.isSelected())) {
                phoneValidation.setStyle("-fx-text-fill: red");
                phoneValidation.setStyle("-fx-text-fill: red");
            }
        }
    }

    public void goToNormalUserSignUpView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Paths.normalUserSignUpView));
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
        companyName.setStyle("-fx-text-box-border: black");
        siret.setStyle("-fx-text-box-border: black");
        nickname.setStyle("-fx-text-box-border: black");
        password1.setStyle("-fx-text-box-border: black");
        password2.setStyle("-fx-text-box-border: black");
        phoneValidation.setStyle("-fx-text-fill: black");
        emailValidation.setStyle("-fx-text-fill: black");
    }

}
