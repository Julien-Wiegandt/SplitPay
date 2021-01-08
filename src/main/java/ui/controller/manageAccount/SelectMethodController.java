package ui.controller.manageAccount;

import core.facade.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import main.SplitPay;
import ui.path.AuthPath;
import ui.path.UserNavigationPath;

import java.io.IOException;

public class SelectMethodController {

    @FXML
    private RadioButton email;

    @FXML
    private RadioButton sms;

    @FXML
    private Button cancel;

    @FXML
    private Button confirm;

    @FXML
    void initialize() {
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'selectMethod.fxml'.";
        assert sms != null : "fx:id=\"sms\" was not injected: check your FXML file 'selectMethod.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'selectMethod.fxml'.";
        assert confirm != null : "fx:id=\"confirm\" was not injected: check your FXML file 'selectMethod.fxml'.";

    }

    @FXML
    void emailMethod() {

    }

    @FXML
    void smsMethod() {

    }
    /**
     * This method redirects to the forgottenPasswordView
     * @throws IOException
     */
    public void goToVerificationView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.verificationView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }


    /**
     * This method redirects to the homeView
     * @throws IOException
     */

    public void goToHomeView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.homeView));
        SplitPay.window.setScene(new Scene(root));
    }

}

