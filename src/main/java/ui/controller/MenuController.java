package ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.SplitPay;
import ui.path.AuthPath;
import ui.path.NormalUserNavigationPath;

import java.io.IOException;

public class MenuController {

    /**
     * This method redirects to the myTransactionsView
     * @param actionEvent
     * @throws IOException
     */
    public void goToNormalUserMyTransactionsView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(NormalUserNavigationPath.myTransactionsView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method redirects to the myTransactionsView
     * @param actionEvent
     * @throws IOException
     */
    public void goToNormalUserMySplitsView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(NormalUserNavigationPath.mySplitsView));
        SplitPay.window.setScene(new Scene(root));
    }

}
