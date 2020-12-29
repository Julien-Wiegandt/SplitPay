package ui.controller;

import core.facade.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.SplitPay;
import ui.path.AuthPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VerificationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField code;

    @FXML
    private Button cancel;

    @FXML
    private Button confirm;

    @FXML
    void initialize() {
        assert code != null : "fx:id=\"code\" was not injected: check your FXML file 'verificationView.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'verificationView.fxml'.";
        assert confirm != null : "fx:id=\"confirm\" was not injected: check your FXML file 'verificationView.fxml'.";

    }
    /**
     * This method verify if the validation code is correct
     * @param actionEvent
     * @throws IOException
     */
    public void verifyCode(ActionEvent actionEvent) throws IOException {
        System.out.println(code.getText());
        System.out.println(UserFacade.getUserFacade().getLoggedUser().getValidationCode());
        if (code.getText().equals(UserFacade.getUserFacade().getLoggedUser().getValidationCode())) {
            Parent root = FXMLLoader.load(getClass().getResource(AuthPath.changePasswordView));
            SplitPay.window.setScene(new Scene(root, 320, 500));
        }
    }
}