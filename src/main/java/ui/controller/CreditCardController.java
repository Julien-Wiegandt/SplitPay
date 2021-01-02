package ui.controller;

import core.facade.CreditCardFacade;
import core.models.CreditCard;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import main.SplitPay;
import ui.path.NormalUserNavigationPath;


import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class CreditCardController {
    @FXML
    private Button addCreditCard;

    @FXML
    private ListView listView;

    @FXML
    private Button retour;


    @FXML
    private void initialize() {
        Collection transactions = CreditCardFacade.getInstance().getCards();
        ObservableList<CreditCard> items = FXCollections.observableArrayList();

        Iterator<CreditCard> iterator = transactions.iterator();
        while (iterator.hasNext()) {
            items.add(iterator.next());
        }
        listView.setItems(items);
    }


    /**

     * @throws IOException
     * @todo Handle the possible exceptions. (if the user click on empty field)
     */
    public void goToCreditCardView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(NormalUserNavigationPath.editCreditCardView));
        Parent root = loader.load();
        EditCreditCardController editCreditCardController = loader.getController();
        editCreditCardController.setCreditCard((CreditCard) listView.getSelectionModel().getSelectedItem());
        SplitPay.window.setScene(new Scene(root));
    }

    public void goToAddCreditCardView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(NormalUserNavigationPath.addCreditCardView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void goToHomeView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(NormalUserNavigationPath.homeView));
        SplitPay.window.setScene(new Scene(root));
    }



}
