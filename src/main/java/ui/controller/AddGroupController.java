package ui.controller;

import core.facade.BankAccountFacade;
import core.facade.GroupFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import main.SplitPay;
import ui.path.NormalUserNavigationPath;
import ui.path.UserNavigationPath;
import util.RegexPattern;

import java.io.IOException;

public class AddGroupController {

    @FXML
    private TextField label;

    public void addGroup(ActionEvent actionEvent) {
        allStyleSetDefault();
        if(RegexPattern.labelPattern.matcher(label.getText()).find()){
            GroupFacade.getInstance().addGroup(label.getText());
        }else{
            label.setStyle("-fx-text-box-border: red");
        }

    }

    public void goToGroupView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(NormalUserNavigationPath.groupView));
        SplitPay.window.setScene(new Scene(root));
    }

    private void allStyleSetDefault() {
        label.setStyle("-fx-text-box-border: black");
    }
}

