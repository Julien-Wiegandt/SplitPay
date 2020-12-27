package core.models;

import java.util.Date;

public class UserToBankAccount extends Transaction{

    public UserToBankAccount(Float amount, Date dateCreated, int sender_fk, int receiver_fk) {
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
        return "To BankAccount " + this.getReceiver_fk() + ": -" + this.getAmount() + "€ on " + this.getDateCreated().toString();
    }

    private int sender_fk;

    private int receiver_fk;
}
