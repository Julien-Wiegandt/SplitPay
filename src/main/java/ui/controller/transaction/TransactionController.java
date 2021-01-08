package ui.controller.transaction;

import core.facade.UserFacade;
import core.models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import main.SplitPay;
import ui.path.UserNavigationPath;

import java.io.IOException;

/**
 * Controller of the transactionView called when a user want to see the details of the selected transaction on myTransactionsView.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2020-12-29
 */
public class TransactionController {

    /**
     * The Transaction's information.
     */
    @FXML
    private Label transactionType, transactionDate, transactionAmount, splitParticipants, otherTransactionActor;

    /**
     * This method redirects to the myTransactionsView
     * @throws IOException
     */
    public void goToMyTransactionsView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.myTransactionsView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method set all the transaction's details in the Labels
     * @param transaction
     */
    public void setTransaction(Transaction transaction){
        if(transaction.getName().equals("UserToUserTransaction")){
            UserToUserTransaction t = (UserToUserTransaction) transaction;
            if(t.getSender_fk() == Integer.valueOf(UserFacade.getUserFacade().getLoggedUser().getId())) {
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
            CreditCardToUserTransaction t = (CreditCardToUserTransaction) transaction;
            otherTransactionActor.setText("From : "+String.valueOf(t.getReceiver_fk()));
        }
        transactionType.setText(transaction.getName());
        transactionDate.setText(transaction.getDateCreated().toString());
        transactionAmount.setText(String.valueOf(transaction.getAmount()+"€"));
    }
}
