package ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.SplitPay;
import ui.path.AuthPath;
import java.io.IOException;

public class ManageAccountController {










    /**
     * This method redirects to the selectMethodView
     * @param actionEvent
     * @throws IOException
     */

    public void goToSelectMethodView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.selectMethodView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the changePhoneView
     * @param actionEvent
     * @throws IOException
     */

    public void goToChangePhoneView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.changePhoneView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the changeEmailView
     * @param actionEvent
     * @throws IOException
     */

    public void goToChangeEmailView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.changeEmailView));
        SplitPay.window.setScene(new Scene(root));
    }


}
