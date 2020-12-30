package ui.controller;

import core.facade.BankAccountFacade;
import core.facade.GroupFacade;
import core.models.BankAccount;
import core.models.Group;
import core.models.NormalUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import main.SplitPay;
import ui.path.NormalUserNavigationPath;
import ui.path.UserNavigationPath;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class GroupController {

    @FXML
    private Button addCreditCard;

    @FXML
    private ListView listView;

    @FXML
    private Button retour;


    @FXML
    private void initialize() {
        Collection transactions = GroupFacade.getInstance().getAllGroup();
        ObservableList<Group> items = FXCollections.observableArrayList();

        Iterator<Group> iterator = transactions.iterator();
        while (iterator.hasNext()) {
            items.add(iterator.next());
        }
        listView.setItems(items);
    }

    public void goToGroupView(ActionEvent actionEvent)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(NormalUserNavigationPath.groupView));
        SplitPay.window.setScene(new Scene(root));
    }
    public void deleteGroup(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(NormalUserNavigationPath.deleteGroupView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void goToAddGroupView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(NormalUserNavigationPath.addGroupView));
        SplitPay.window.setScene(new Scene(root));

    }

    public void goToHomeView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(UserNavigationPath.homeView));
        SplitPay.window.setScene(new Scene(root));

    }
}
