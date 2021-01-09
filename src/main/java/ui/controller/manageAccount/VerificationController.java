package ui.controller.manageAccount;

import core.facade.UserFacade;
import core.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import main.SplitPay;
import ui.path.AuthPath;
import util.SplitUtilities;

import java.io.IOException;

public class VerificationController {

    private static User tempUser;
    @FXML
    private TextField code;
    private String realCode;

    public static void setTempUser(User tempUser) {
        VerificationController.tempUser = tempUser;
    }

    @FXML
    void initialize() {
        realCode = SplitUtilities.generateCode();
        System.out.println(realCode);
    }

    /**
     * This method redirects to the selectMethodView
     *
     * @throws IOException
     */
    public void goToManageAccountView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.manageAccountView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method verify if the validation code is correct and update the user
     *
     * @throws IOException
     */
    public void verifyCode() throws IOException {
        System.out.println(code.getText());
        System.out.println(realCode);
        if (code.getText().equals(realCode)) {
            UserFacade.getUserFacade().updateUser(tempUser);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.manageAccountView));
            SplitPay.window.setScene(new Scene(root));

        }
    }
}