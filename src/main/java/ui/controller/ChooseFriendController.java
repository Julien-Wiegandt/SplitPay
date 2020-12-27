package ui.controller;

import core.facade.TransactionFacade;
import core.facade.UserFacade;
import core.models.NormalUser;
import core.models.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import main.SplitPay;
import ui.path.AuthPath;
import ui.path.NormalUserNavigationPath;
import ui.path.UserNavigationPath;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class ChooseFriendController {

    @FXML
    ListView friendsList;


    @FXML
    private void initialize() {
        Collection friends = UserFacade.getUserFacade().getFriends(Integer.valueOf(UserFacade.getUserFacade().getLoggedNormalUser().getId()));
        ObservableList<NormalUser> items = FXCollections.observableArrayList ();
        Iterator<NormalUser> iterator = friends.iterator();
        while (iterator.hasNext()) {
            items.add(iterator.next());
        }
        friendsList.setItems(items);
    }

    public void goToChooseAmountView(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(NormalUserNavigationPath.chooseAmountView));
        Parent root = loader.load();
        ChooseAmountController chooseAmountController = loader.getController();
        chooseAmountController.setFriend((NormalUser) friendsList.getSelectionModel().getSelectedItem());
        SplitPay.window.setScene(new Scene(root));
    }
}
