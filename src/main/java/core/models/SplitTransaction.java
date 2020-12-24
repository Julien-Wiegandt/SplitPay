package core.models;

import java.util.Date;

public class SplitTransaction extends Transaction{

    public SplitTransaction(Float amount, Date dateCreated, NormalUser sender_fk, StoreOwner receiver_fk) {
        this.amount = amount;
        this.dateCreated = dateCreated;
        this.sender_fk = sender_fk;
        this.receiver_fk = receiver_fk;
    }

    public NormalUser getSender_fk() {
        return sender_fk;
    }

    public void setSender_fk(NormalUser sender_fk) {
        this.sender_fk = sender_fk;
    }

    public StoreOwner getReceiver_fk() {
        return receiver_fk;
    }

    public void setReceiver_fk(StoreOwner receiver_fk) {
        this.receiver_fk = receiver_fk;
    }

    private NormalUser sender_fk;

    private StoreOwner receiver_fk;

}
