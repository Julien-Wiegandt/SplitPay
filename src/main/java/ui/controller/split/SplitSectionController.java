package ui.controller.split;

import client.facade.SplitClientFacade;
import core.facade.UserFacade;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.SplitPay;
import ui.path.UserNavigationPath;

import java.io.IOException;
import java.net.URL;

public class SplitSectionController {

    private final SplitClientFacade facade = SplitClientFacade.getInstance();

    @FXML
    private AnchorPane splitSection;

    @FXML
    private TextField splitCode;

    @FXML
    private Label splitSectionFlashMessage;

    /**
     * This methods sends the splitCode to the server to attempt joining a split
     */
    public void joinSplit() {
        facade.joinSplit(splitCode.getText());
    }

    /**
     * Method used to store the joined split and redirecting the user to the saloon
     */
    public void splitJoined() {
        Platform.runLater(() -> goToSplitSaloonView());
    }

    /**
     * Method used to redirect the user into the split saloon
     * chooses the right view according to the split mode
     */
    private void goToSplitSaloonView() {
        URL url;
        switch (facade.getJoinedSplit().getSplitMode()) {
            case ITEMSPLIT:
                url = getClass().getClassLoader().getResource(UserNavigationPath.itemSplitSaloonView);
                break;
            case FREESPLIT:
                url = getClass().getClassLoader().getResource(UserNavigationPath.freeSplitSaloonView);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + facade.getJoinedSplit().getSplitMode());
        }

        Parent root = null;
        try {
            // TODO : Handle resource path problem
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SplitPay.window.setScene(new Scene(root));
    }

    @FXML
    public void setFlashMessage(String flashMessage) {
        System.out.println("ok");
        Platform.runLater(() -> splitSectionFlashMessage.setText(flashMessage));
    }

    @FXML
    private void initialize() {
        if (UserFacade.getUserFacade().isStoreOwner()) {
            splitSection.setVisible(false);
            splitSection.setManaged(false);
        }
        SplitClientFacade.getInstance().setSplitSectionController(this);
    }

}
