package ui.controller.manageGroup;

import core.facade.GroupFacade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import main.SplitPay;
import ui.path.NormalUserNavigationPath;
import util.RegexPattern;

import java.io.IOException;

public class AddGroupController {

    @FXML
    private TextField label;

    public void addGroup() throws IOException {
        allStyleSetDefault();
        if (RegexPattern.labelPattern.matcher(label.getText()).find()) {
            GroupFacade.getInstance().addGroup(label.getText());
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(NormalUserNavigationPath.groupView));
            SplitPay.window.setScene(new Scene(root));
        } else {
            label.setStyle("-fx-text-box-border: red");
        }

    }

    public void goToGroupView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(NormalUserNavigationPath.groupView));
        SplitPay.window.setScene(new Scene(root));
    }

    private void allStyleSetDefault() {
        label.setStyle("-fx-text-box-border: black");
    }
}

