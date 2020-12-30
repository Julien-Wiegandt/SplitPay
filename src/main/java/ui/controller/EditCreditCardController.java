package ui.controller;

import core.facade.CreditCardFacade;

import core.models.CreditCard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;

import main.SplitPay;
import ui.path.NormalUserNavigationPath;


import java.io.IOException;

import javafx.event.ActionEvent;

public class EditCreditCardController {


    @FXML
    private Label number;

    @FXML
    private Label nameOwner;



    private String card_number;

    public void setCreditCard(CreditCard selectedItem) {

        this.card_number = selectedItem.getNumber();

        number.setText(selectedItem.getNumber());

        nameOwner.setText(selectedItem.getNameOwner());

    }




    public void deleteCreditCard( ActionEvent actionEvent) throws IOException {
        CreditCardFacade.getInstance().deleteCreditCard(this.card_number,null,null,null);
        Parent root = FXMLLoader.load(getClass().getResource(NormalUserNavigationPath.creditCardView));
        SplitPay.window.setScene(new Scene(root));
    }
}
