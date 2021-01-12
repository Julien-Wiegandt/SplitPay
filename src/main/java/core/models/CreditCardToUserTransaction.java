package core.models;

import java.util.Date;

/**
 * Model of the CreditCardToUserTransaction which represents the transaction from a CreditCard to a NormalUser's balance.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2021-01-05
 */
public class CreditCardToUserTransaction extends Transaction {

    /**
     * CreditCardToUserTransaction's constructor.
     *
     * @param amount
     * @param dateCreated
     * @param sender_fk
     * @param receiver_fk
     */
    public CreditCardToUserTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk) {
        this.name = "CreditCardToUserTransaction";
        this.amount = amount;
        this.dateCreated = dateCreated;
        this.sender_fk = sender_fk;
        this.receiver_fk = receiver_fk;
    }

    public int getSender_fk() {
        return sender_fk;
    }

    public void setSender_fk(int sender_fk) {
        this.sender_fk = sender_fk;
    }

    public int getReceiver_fk() {
        return receiver_fk;
    }

    public void setReceiver_fk(int receiver_fk) {
        this.receiver_fk = receiver_fk;
    }

    /**
     * Represents the transaction.
     *
     * @return the String representation of the transaction.
     */
    public String toString() {
        return "From CreditCard " + this.getSender_fk() + ": +" + this.getAmount() + "€ on " + this.getDateCreated().toString();
    }

    private int sender_fk;

    private int receiver_fk;
}
