package ui.controller.split;

import client.facade.SplitClientFacade;
import core.facade.BillFacade;
import core.facade.UserFacade;
import core.models.Bill;
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

public class GenerateSplitViewController {

    private SplitClientFacade facade = SplitClientFacade.getInstance();

    private ArrayList<Bill> billsAvailable;

    @FXML
    private ListView<Bill> listView;

    @FXML
    private TextField splitLabel;

    @FXML
    private Label flashMessage;


    /**
     * utility method to fetch store owners in the database
     * @throws IOException
     */
    private void getStoreOwners() throws IOException {
        System.out.println("Get store owners");
        try {
            billsAvailable = (ArrayList<Bill>) BillFacade.getInstance().getLoggedUserBills();
            System.out.println("store owners availalble : " + billsAvailable);
        } catch (SQLException throwables) {
            // TODO : handle error display
            throwables.printStackTrace();
        }
    }

    public void setBills(){
        ObservableList<Bill> items = FXCollections.observableArrayList ();
        items.setAll(billsAvailable);
        listView.setItems(items);
    }

    public void splitCreatedSuccess(String splitCode){
        Platform.runLater(() -> {
                    setFlashMessage("Split created, join with code : "+splitCode);
                }
        );
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
    public void generateItemSplit(){
        resetFlashMessage();
        String label = splitLabel.getText();
        Bill selectedBill = listView.getSelectionModel().getSelectedItem();
        try {
            facade.createItemSplit(label,selectedBill);
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
        setBills();
        splitLabel.setText(UserFacade.getUserFacade().getLoggedStoreOwner().getCompanyName()+"'s split");
        SplitClientFacade.getInstance().setSplitGenerationController(this);
    }

}