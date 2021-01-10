package ui.controller.split;

import client.facade.SplitClientFacade;
import core.facade.UserFacade;
import core.models.StoreOwner;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import server.models.split.Split;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SplitCreationController {

    private HashMap<String, Split> splits = null;

    private SplitClientFacade facade = SplitClientFacade.getInstance();

    private ArrayList<StoreOwner> storeOwnersAvailable;

    @FXML
    private ListView<StoreOwner> listView;

    @FXML
    private TextField splitLabel;

    @FXML
    private TextField splitGoalAmount;

    @FXML
    private Label flashMessage;


    /**
     * utility method to fetch store owners in the database
     * @throws IOException
     */
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

    public void splitCreatedSuccess(String splitCode){
        Platform.runLater(() -> {
                    setFlashMessage("Split created, join with code : "+splitCode);
                    clearInputs();
                }
        );
    }

    private void clearInputs(){
        splitLabel.setText("");
        splitGoalAmount.setText("");
    }

    /**
     * Sets flash message
     * @param message
     */
    public void setFlashMessage(String message){
        flashMessage.setText(message);
    }

    /**
     * utility method to reset flash message
     */
    private void resetFlashMessage(){
        flashMessage.setText("");
    }

    /**
     * Harvests ui input and sends the data to the facade
     */
    public void createFreeSplit(){
    resetFlashMessage();
    double goalAmount = Double.parseDouble(splitGoalAmount.getText());
        String label = splitLabel.getText();
        StoreOwner receiver = listView.getSelectionModel().getSelectedItem();
        try {
            facade.createFreeSplit(label,goalAmount,receiver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO : implement
    public void createEqualSplit(){

    }

    @FXML
    private void initialize() throws IOException {
        getStoreOwners();
        setStoreOwners();
        SplitClientFacade.getInstance().setSplitCreationController(this);
    }

}
