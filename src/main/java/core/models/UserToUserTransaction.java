package core.models;

import core.facade.UserFacade;

import java.util.Date;

public class UserToUserTransaction extends Transaction{

    public UserToUserTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk) {
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
        if(this.getAmount()<0){
            return "To User " + this.getReceiver_fk() + ": -" + this.getAmount() + "€ on " + this.getDateCreated().toString();
        }else{
            return "From User " + this.getSender_fk() + ": +" + this.getAmount() + "€ on " + this.getDateCreated().toString();
        }
    }

    private int sender_fk;

    private int receiver_fk;

}
