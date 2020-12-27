package ui.controller;

import core.facade.TransactionFacade;
import core.facade.UserFacade;
import core.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;
import java.util.Collection;
import java.util.Iterator;

public class TransactionController {

    @FXML
    private Label transactionType, transactionDate, transactionAmount, splitParticipants, otherTransactionActor;

    void setTransaction(Transaction transaction){
        System.out.println(transaction.toString());
        if(transaction.getName().equals("UserToUserTransaction")){
            UserToUserTransaction t = (UserToUserTransaction) transaction;
            if(t.getSender_fk() == Integer.valueOf(UserFacade.getUserFacade().getLoggedUser().getId())) {
                otherTransactionActor.setText("To : "+String.valueOf(t.getReceiver_fk()));
            }else{
                otherTransactionActor.setText("From : "+String.valueOf(t.getSender_fk()));
            }
        }else if(transaction.getName().equals("UserToStoreOwnerTransaction")){
            UserToStoreOwnerTransaction t = (UserToStoreOwnerTransaction) transaction;
            if(UserFacade.getUserFacade().isNormalUser()) {
                otherTransactionActor.setText("To : "+String.valueOf(t.getReceiver_fk()));
            }else{
                otherTransactionActor.setText("From : "+String.valueOf(t.getSender_fk()));
            }
        }else if(transaction.getName().equals("UserToBankAccount")){
            UserToBankAccount t = (UserToBankAccount) transaction;
            otherTransactionActor.setText("To : "+String.valueOf(t.getReceiver_fk()));
        }else if(transaction.getName().equals("StoreOwnerToBankAccount")){
            StoreOwnerToBankAccount t = (StoreOwnerToBankAccount) transaction;
            otherTransactionActor.setText("To : "+String.valueOf(t.getReceiver_fk()));
        }else if(transaction.getName().equals("SplitTransaction")){
            SplitTransaction t = (SplitTransaction) transaction;
            splitParticipants.setText("Participants : "+t.getParticipants());
            otherTransactionActor.setText("To : "+String.valueOf(t.getReceiver_fk()));
        }else if(transaction.getName().equals("BankAccountToUserTransaction")){
            BankAccountToUserTransaction t = (BankAccountToUserTransaction) transaction;
            otherTransactionActor.setText("From : "+String.valueOf(t.getReceiver_fk()));
        }
        transactionType.setText(transaction.getName());
        transactionDate.setText(transaction.getDateCreated().toString());
        transactionAmount.setText(String.valueOf(transaction.getAmount()+"€"));
    }

    @FXML
    private void initialize() {
    }
}
