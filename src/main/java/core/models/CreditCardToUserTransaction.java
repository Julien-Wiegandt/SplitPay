package core.models;

import java.util.Date;

public class CreditCardToUserTransaction extends Transaction{

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

    public String toString(){
        return "From CreditCard " + this.getSender_fk() + ": +" + this.getAmount() + "€ on " + this.getDateCreated().toString();
    }

    private int sender_fk;

    private int receiver_fk;
}
