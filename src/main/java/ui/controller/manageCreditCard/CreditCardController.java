package ui.controller.manageCreditCard;

import core.facade.CreditCardFacade;
import core.models.CreditCard;
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
        if(listView.getSelectionModel().getSelectedItem() !=null) {


            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(NormalUserNavigationPath.editCreditCardView));
            Parent root = loader.load();
            EditCreditCardController editCreditCardController = loader.getController();
            editCreditCardController.setCreditCard((CreditCard) listView.getSelectionModel().getSelectedItem());
            SplitPay.window.setScene(new Scene(root));
        }
    }

    public void goToAddCreditCardView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(NormalUserNavigationPath.addCreditCardView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void goToHomeView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.homeView));
        SplitPay.window.setScene(new Scene(root));
    }



}
