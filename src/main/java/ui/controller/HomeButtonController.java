package ui.controller;

import client.facade.SplitClientFacade;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.SplitPay;
import ui.path.UserNavigationPath;

import java.io.IOException;

public class HomeButtonController {

    private final SplitClientFacade facade = SplitClientFacade.getInstance();

    /**
     * Handles participant quit button
     */
    public void quitHandler() {
        facade.quitSplitView();
    }

    /**
     * Method called when the participant successfully quit the split
     */
    public void splitQuit() {
        Platform.runLater(() -> goToHomeView());
    }

    /**
     * This method redirects to the myTransactionsView
     *
     * @throws IOException
     */
    public void goToHomeView() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.homeView));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SplitPay.window.setScene(new Scene(root));
    }
}
