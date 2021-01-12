package ui.controller.manageBankAccount;

import core.facade.BankAccountFacade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import main.SplitPay;
import ui.path.UserNavigationPath;
import util.RegexPattern;

import java.io.IOException;

public class AddBankAccountController {
    @FXML
    private TextField label;
    @FXML
    private TextField bic;
    @FXML
    private TextField iban;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;


    public void addBankAccount() throws IOException {
        allStyleSetDefault();

        if (RegexPattern.labelPattern.matcher(label.getText()).find() &&
                RegexPattern.bicPattern.matcher(bic.getText()).find() &&
                RegexPattern.ibanPattern.matcher(iban.getText()).find() &&
                RegexPattern.namePattern.matcher(firstName.getText()).find() &&
                RegexPattern.namePattern.matcher(lastName.getText()).find()) {

            BankAccountFacade.getInstance().createBankAccount(label.getText(), bic.getText(), iban.getText(), firstName.getText(), lastName.getText());
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.bankAccountView));
            SplitPay.window.setScene(new Scene(root));
        } else {
            if (!RegexPattern.labelPattern.matcher(label.getText()).find()) {
                label.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.bicPattern.matcher(bic.getText()).find()) {
                bic.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.ibanPattern.matcher(iban.getText()).find()) {
                iban.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.namePattern.matcher(firstName.getText()).find()) {
                firstName.setStyle("-fx-text-box-border: red");
            }
            if (!RegexPattern.namePattern.matcher(lastName.getText()).find()) {
                lastName.setStyle("-fx-text-box-border: red");
            }
        }

    }

    public void goToBankAccountView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.bankAccountView));
        SplitPay.window.setScene(new Scene(root));
    }


    private void allStyleSetDefault() {
        label.setStyle("-fx-text-box-border: black");
        bic.setStyle("-fx-text-box-border: black");
        iban.setStyle("-fx-text-box-border: black");
        firstName.setStyle("-fx-text-box-border: black");
        lastName.setStyle("-fx-text-box-border: black");
    }
}
