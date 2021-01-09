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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class FreeSplitCreationController {

    private HashMap<String, Split> splits = null;

    private SplitClientFacade facade = SplitClientFacade.getInstance();

    private ArrayList<StoreOwner> storeOwnersAvailable;

    @FXML
    private ListView<StoreOwner> listView;

    @FXML
    private TextField splitLabel;

    @FXML
    private TextField splitGoalAmount;


    private void getStoreOwners() throws IOException {
        System.out.println("Get store owners");
        try {
            storeOwnersAvailable = UserFacade.getUserFacade().getAllStoreOwners();
            System.out.println("store owners availalble : " + storeOwnersAvailable);
        } catch (SQLException throwables) {
            // TODO : handle error display
            throwables.printStackTrace();
        }
    }

    public void setStoreOwners(){
        ObservableList<StoreOwner> items = FXCollections.observableArrayList ();
        items.setAll(storeOwnersAvailable);
        listView.setItems(items);
    }

    @FXML
    private void initialize() throws IOException {
        getStoreOwners();
        setStoreOwners();
    }

}
