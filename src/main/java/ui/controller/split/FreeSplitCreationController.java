package ui.controller.split;

import client.facade.SplitClientFacade;
import core.facade.UserFacade;
import core.models.StoreOwner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import server.models.split.Split;

import java.io.IOException;
import java.util.HashMap;

public class FreeSplitCreationController {

    private HashMap<String, Split> splits = null;

    private SplitClientFacade facade = SplitClientFacade.getInstance();

    @FXML
    private ListView<StoreOwner> listView;

    @FXML
    private TextField splitLabel;

    @FXML
    private TextField splitGoalAmount;

    // TODO : implement
    public void getStoreOwners() throws IOException {
        System.out.println("Get store owners");
//        UserFacade.getUserFacade().
    }

    public void setStoreOwners(HashMap<String, Split> splits){
        ObservableList<StoreOwner> items = FXCollections.observableArrayList ();
//        items.setAll();
        listView.setItems(items);
    }

    @FXML
    private void initialize() throws IOException {
        getStoreOwners();
    }

}
