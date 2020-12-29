package ui.controller;

import core.facade.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.path.AuthPath;
import utilities.RegexPattern;
import main.SplitPay;
import java.io.IOException;

public class StoreOwnerSignUpController {

    @FXML
    private TextField credential, nickname, companyName, siret;
    @FXML
    private PasswordField password1, password2;
    @FXML
    private CheckBox isCompany;

    public void signUp(ActionEvent actionEvent) {
        allStyleSetDefault();
        if(RegexPattern.emailPattern.matcher(credential.getText()).find()
                && RegexPattern.textPattern.matcher(companyName.getText()).find()
                && RegexPattern.siretPattern.matcher(siret.getText()).find()
                && RegexPattern.nicknamePattern.matcher(nickname.getText()).find()
                && RegexPattern.passwordPattern.matcher(password1.getText()).find()
                && (password1.getText().equals(password2.getText()))){
            UserFacade.getUserFacade().storeOwnerEmailSignUp(credential.getText(), companyName.getText(), nickname.getText(), siret.getText(), password1.getText());
        }else if(RegexPattern.phonePattern.matcher(credential.getText()).find()
                && RegexPattern.textPattern.matcher(companyName.getText()).find()
                && RegexPattern.siretPattern.matcher(siret.getText()).find()
                && RegexPattern.nicknamePattern.matcher(nickname.getText()).find()
                && RegexPattern.passwordPattern.matcher(password1.getText()).find()
                && (password1.getText().equals(password2.getText()))) {
            UserFacade.getUserFacade().storeOwnerPhoneSignUp(credential.getText(), companyName.getText(), nickname.getText(), siret.getText(), password1.getText());
        }else {
            if (!RegexPattern.emailPattern.matcher(credential.getText()).find() && !RegexPattern.phonePattern.matcher(credential.getText()).find()) {
                credential.setStyle("-fx-text-box-border: red");
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
        }
    }

    public void goToNormalUserSignUpView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.normalUserSignUpView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

    public void goToLogInView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.logInView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

    private void allStyleSetDefault(){
        credential.setStyle("-fx-text-box-border: black");
        credential.setStyle("-fx-text-box-border: black");
        companyName.setStyle("-fx-text-box-border: black");
        siret.setStyle("-fx-text-box-border: black");
        nickname.setStyle("-fx-text-box-border: black");
        password1.setStyle("-fx-text-box-border: black");
        password2.setStyle("-fx-text-box-border: black");
    }

}
