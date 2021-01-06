package ui.controller;

import core.facade.UserFacade;
import core.models.CreditCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import main.SplitPay;
import ui.path.UserNavigationPath;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * Controller of the chooseCreditCardView called when the current user has to choose
 * a CreditCard to refill his balance.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2020-12-28
 */
public class ChooseCreditCardController {

    /**
     * Contains all the current user's CredCards.
     */
    @FXML
    private ListView listView;

    /**
     This method transfers the selected CreditCard in the ChooseRefillAmountController and load the chooseRefillAmountView.
     * It is called by a button.
     * @throws IOException
     */
    public void goToChooseRefillAmountView() throws IOException {
        if(listView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(UserNavigationPath.chooseRefillAmountView));
            Parent root = loader.load();
            ChooseRefillAmountController chooseRefillAmountController = loader.getController();
            chooseRefillAmountController.setCreditCard((CreditCard) listView.getSelectionModel().getSelectedItem());
            SplitPay.window.setScene(new Scene(root));
        }
    }

    /**
     * This method fill the listView with all the current user's CreditCards.
     * It is called at the view's load.
     */

    @FXML
    private void initialize() {
        Collection<CreditCard> creditCards = UserFacade.getUserFacade().getCreditCards();

        ObservableList<CreditCard> items = FXCollections.observableArrayList ();

        Iterator<CreditCard> iterator = creditCards.iterator();
        while (iterator.hasNext()) {
            items.add(iterator.next());
        }
        listView.setItems(items);
    }
}
