package ui.controller;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import core.facade.CreditCardFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import main.SplitPay;
import ui.path.AuthPath;
import ui.path.NormalUserNavigationPath;
import util.RegexPattern;

public class AddCreditCardController {
    @FXML
    private TextField number;
    @FXML
    private TextField nameOwner;
    @FXML
    private TextField date;
    @FXML
    private TextField cvv;

    public void addCard(ActionEvent actionEvent) throws ParseException {
        allStyleSetDefault();

        if(RegexPattern.numberPattern.matcher(number.getText()).find()
                && RegexPattern.nameOwnerPattern.matcher(nameOwner.getText()).find()
                && RegexPattern.datePattern.matcher(date.getText()).find()
                && RegexPattern.cvvPattern.matcher(cvv.getText()).find()
                ){
            Date real_date = new SimpleDateFormat("yyyy-MM-dd").parse(date.getText());
            CreditCardFacade.getInstance().createCreditCard(number.getText(),nameOwner.getText(),real_date,cvv.getText());

        }else{
            if(!RegexPattern.numberPattern.matcher(number.getText()).find()){
                number.setStyle("-fx-text-box-border: red");
            }
            if(!RegexPattern.nameOwnerPattern.matcher(nameOwner.getText()).find()){
                nameOwner.setStyle("-fx-text-box-border: red");
            }

            if(!RegexPattern.datePattern.matcher(date.getText()).find()){
                date.setStyle("-fx-text-box-border: red");
            }

            if(!RegexPattern.cvvPattern.matcher(cvv.getText()).find()){
                cvv.setStyle("-fx-text-box-border: red");
            }
        }

    }

    private void allStyleSetDefault(){
        number.setStyle("-fx-text-box-border: black");
        nameOwner.setStyle("-fx-text-box-border: black");
        date.setStyle("-fx-text-box-border: black");
        date.setStyle("-fx-text-box-border: black");
        cvv.setStyle("-fx-text-box-border: black");
    }



    public void goToCreditCardView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(NormalUserNavigationPath.creditCardView));
        SplitPay.window.setScene(new Scene(root));
    }
}
