package core.models;

import java.util.Date;

/**
 * Model of the SplitTransaction which represents the transaction from a NormalUser's balance to a StoreOwner's balance.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2021-01-05
 */
public class SplitTransaction extends Transaction{

     /**
     * SplitTransaction's constructor.
     * @param amount
     * @param dateCreated
     * @param sender_fk
     * @param receiver_fk
     * @param participants
     */
    public SplitTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk, String participants) {
        this.name = "SplitTransaction";
        this.amount = amount;
        this.dateCreated = dateCreated;
        this.sender_fk = sender_fk;
        this.receiver_fk = receiver_fk;
        this.participants = participants;
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

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    /**
     * Represents the transaction.
     * @return the String representation of the transaction.
     */
    public String toString(){
        return "To SplitPay: -" + this.getAmount() + "€ on " + this.getDateCreated().toString();
    }

    private int sender_fk;

    private int receiver_fk;

    private String participants;

}
