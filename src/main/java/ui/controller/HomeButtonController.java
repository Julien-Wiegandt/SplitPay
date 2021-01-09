package ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.SplitPay;
import ui.path.UserNavigationPath;

import java.io.IOException;

public class HomeButtonController {

    /**
     * This method redirects to the myTransactionsView
     *
     * @param actionEvent
     * @throws IOException
     */
    public void goToHomeView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.homeView));
        SplitPay.window.setScene(new Scene(root));
    }
}
