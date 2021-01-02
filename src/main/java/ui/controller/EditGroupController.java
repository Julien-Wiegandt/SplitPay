package ui.controller;

import core.facade.GroupFacade;
import core.models.Group;
import core.models.NormalUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import main.SplitPay;
import ui.path.NormalUserNavigationPath;


import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class EditGroupController {

    @FXML
    private Label label;

    @FXML
    private ListView listView;

    private String id_group;
    private String label_group;




    @FXML
    private void initialize() {
    }

    public void setGroup(Group selectedItem) {

        this.id_group = selectedItem.getId();
        this.label_group = selectedItem.getLabel();

        Collection friends = GroupFacade.getInstance().getAllFriendFromGroup(id_group);
        ObservableList<NormalUser> items = FXCollections.observableArrayList();

        Iterator<NormalUser> iterator = friends.iterator();
        while (iterator.hasNext()) {
            items.add(iterator.next());
        }
        listView.setItems(items);

        label.setText(selectedItem.getLabel());



    }

    public void deleteGroup(ActionEvent actionEvent) throws IOException {
        GroupFacade.getInstance().deleteGroup(id_group, label_group);
        Parent root = FXMLLoader.load(getClass().getResource(NormalUserNavigationPath.groupView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void goToGroupView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(NormalUserNavigationPath.groupView));
        SplitPay.window.setScene(new Scene(root));
    }
}
