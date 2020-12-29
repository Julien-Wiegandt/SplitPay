package ui.controller;

import core.auth.Session;
import core.facade.FriendFacade;
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
import utilities.RegexPattern;
import utilities.SplitUtilities;

import java.io.IOException;
import java.sql.SQLException;

public class ChangePhoneController {

    @FXML
    private TextField phone;
    @FXML
    private Button cancel;

    @FXML
    private Button confirm;

    @FXML
    void initialize() {

    }

    /**
     * This method changes the user's phone number
     * @param actionEvent
     * @throws IOException
     */
    public void changePhone(ActionEvent actionEvent) throws IOException {
        if (RegexPattern.phonePattern.matcher(phone.getText()).find()) {
            UserFacade.getUserFacade().getUser().setPhone(phone.getText());
            String code = SplitUtilities.generateCode();
            System.out.println(code);
            Parent root = FXMLLoader.load(getClass().getResource(AuthPath.verificationView));
            SplitPay.window.setScene(new Scene(root));

        }
        else {
            phone.setStyle("-fx-text-box-border: red");
        }


    }
}
